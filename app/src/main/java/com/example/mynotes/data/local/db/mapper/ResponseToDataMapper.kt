package com.example.mynotes.data.local.db.mapper

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.data.model.response.TokenResponse
import com.example.mynotes.ui.extensions.emptyString

val SIMPLE_RESPONSE_TO_DATA_MAPPER = object : Mapper<SimpleResponse, SimpleData> {
    override fun map(source: SimpleResponse): SimpleData = SimpleData(
        source.successful ?: false,
        source.message ?: emptyString()
    )
}

val TOKEN_RESPONSE_TO_DATA_MAPPER = object : Mapper<TokenResponse, SimpleData> {
    override fun map(source: TokenResponse): SimpleData = SimpleData(
        source.successful ?: false,
        source.message ?: emptyString()
    )
}