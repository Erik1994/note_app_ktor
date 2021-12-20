import com.example.mynotes.R
import com.example.mynotes.data.local.db.mapper.Mapper
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * helper method for database cached api calls
 */
inline fun <RESULT, RESPONSE> safeCashedApiCall(
    connectionManager: ConnectionManager,
    crossinline databaseSource: () -> Flow<RESULT>,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline databaseEmitter: suspend (RESPONSE) -> Unit,
    crossinline shouldFetchFromApi: (RESULT) -> Boolean = { true }
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    val preCachedData = databaseSource.invoke().catch { }.first()
    val cachedFlow = if (shouldFetchFromApi.invoke(preCachedData)) {
        emit(Resource.Loading(preCachedData))
        try {
            val responseResult = apiCall.invoke()
            if (responseResult.isSuccessful) {
                responseResult.body()?.let {
                    databaseEmitter.invoke(it)
                }
            }
            databaseSource.invoke().map { Resource.Success(it) }
        } catch (t: Throwable) {
            val errorMessage = if (connectionManager.checkNetworkConnection()) {
                t.message ?: context.getString(R.string.fetch_data_error)
            } else {
                context.getString(R.string.internet_connection_error)
            }
            databaseSource.invoke().map {
                Resource.Error(errorMessage, it)
            }.catch {
                Resource.Error(context.getString(R.string.unknown_error), preCachedData)
            }
        }
    } else {
        databaseSource.invoke().map { Resource.Success(it) }
    }
    emitAll(cachedFlow)
}

/**
 * helper method for safe api calls which handles exceptions
 */
inline fun <RESPONSE, RESULT> safeApiCall(
    mapper: Mapper<RESPONSE, RESULT>,
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(mapper.map(it)))
            } ?: emit(Resource.Error( response.message(), null, response.code()))
        } else {
            emit(Resource.Error(response.message(),null,  response.code()))
        }
    } catch (e: Exception) {
        val errorMessage = if (connectionManager.checkNetworkConnection()) {
            e.message
        } else {
            context.getString(R.string.internet_connection_error)
        }
        emit(Resource.Error(errorMessage,null))
    }
}

inline fun <RESULT, RESPONSE> safeCashedApiCall(
    mapper: Mapper<RESPONSE, RESULT>,
    connectionManager: ConnectionManager,
    crossinline databaseSource: () -> Flow<RESULT>,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline databaseEmitter: suspend (RESULT) -> Unit,
    crossinline shouldFetchFromApi: (RESULT) -> Boolean = { true }
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    val preCachedData = databaseSource.invoke().catch { }.first()
    val cachedFlow = if (shouldFetchFromApi.invoke(preCachedData)) {
        emit(Resource.Loading(preCachedData))
        try {
            val responseResult = apiCall.invoke()
            if (responseResult.isSuccessful) {
                responseResult.body()?.let {
                    databaseEmitter.invoke(mapper.map(it))
                }
            }
            databaseSource.invoke().map { Resource.Success(it) }
        } catch (t: Throwable) {
            val errorMessage = if (connectionManager.checkNetworkConnection()) {
                t.message ?: context.getString(R.string.fetch_data_error)
            } else {
                context.getString(R.string.internet_connection_error)
            }
            databaseSource.invoke().map {
                Resource.Error(errorMessage, it)
            }.catch {
                Resource.Error(context.getString(R.string.unknown_error), preCachedData)
            }
        }
    } else {
        databaseSource.invoke().map { Resource.Success(it) }
    }
    emitAll(cachedFlow)
}
