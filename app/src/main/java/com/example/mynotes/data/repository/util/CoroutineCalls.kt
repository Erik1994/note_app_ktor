import com.example.mynotes.R
import com.example.mynotes.data.local.db.mapper.Mapper
import com.example.mynotes.data.local.db.mapper.MapperDataHolder
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * helper method for safe api calls which handles exceptions
 */
inline fun <RESPONSE, RESULT> safeApiCall(
    mapper: Mapper<RESPONSE, RESULT>,
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline onFetchFailed: (Throwable) -> Unit = { }
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(mapper.map(it)))
            } ?: emit(Resource.Error(response.message(), null, response.code()))
        } else {
            emit(Resource.Error(response.message(), null, response.code()))
            onFetchFailed.invoke(Exception(response.message()))
        }
    } catch (e: Exception) {
        val errorMessage = if (connectionManager.checkNetworkConnection()) {
            e.message
        } else {
            context.getString(R.string.internet_connection_error)
        }
        emit(Resource.Error(errorMessage, null))
        onFetchFailed.invoke(e)
    }
}


inline fun <RESPONSE> safeApiCall(
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline onFetchFailed: (Throwable) -> Unit = {}
): Flow<Resource<RESPONSE>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(it))
            } ?: emit(Resource.Error(response.message(), null, response.code()))
        } else {
            emit(Resource.Error(response.message(), null, response.code()))
            onFetchFailed.invoke(Exception(response.message()))
        }
    } catch (e: Exception) {
        val errorMessage = if (connectionManager.checkNetworkConnection()) {
            e.message
        } else {
            context.getString(R.string.internet_connection_error)
        }
        emit(Resource.Error(errorMessage, null))
        onFetchFailed.invoke(e)
    }
}

inline fun <RESPONSE, RESULT> safeCashedApiCall(
    mapper: Mapper<MapperDataHolder<RESPONSE>, RESULT>,
    connectionManager: ConnectionManager,
    crossinline dbQuery: () -> Flow<RESULT>,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline dbSaver: suspend (RESULT) -> Unit,
    crossinline shouldFetchFromApi: (RESULT) -> Boolean = { true },
    crossinline onFetchFailed: (Throwable) -> Unit = {}
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val context = connectionManager.context
    val preCachedData = dbQuery.invoke().catch { }.first()
    val cachedFlow = if (shouldFetchFromApi.invoke(preCachedData)) {
        emit(Resource.Loading(preCachedData))
        try {
            val responseResult = apiCall.invoke()
            if (responseResult.isSuccessful) {
                responseResult.body()?.let {
                    dbSaver.invoke(mapper.map(MapperDataHolder((it))))
                }
            }
            dbQuery.invoke().map { Resource.Success(it) }
        } catch (t: Throwable) {
            val errorMessage = if (connectionManager.checkNetworkConnection()) {
                t.message ?: context.getString(R.string.fetch_data_error)
            } else {
                context.getString(R.string.internet_connection_error)
            }
            onFetchFailed.invoke(t)
            dbQuery.invoke().map {
                Resource.Error(errorMessage, it)
            }.catch {
                onFetchFailed.invoke(t)
                Resource.Error(context.getString(R.string.unknown_error), preCachedData)
            }
        }
    } else {
        dbQuery.invoke().map { Resource.Success(it) }
    }
    emitAll(cachedFlow)
}
