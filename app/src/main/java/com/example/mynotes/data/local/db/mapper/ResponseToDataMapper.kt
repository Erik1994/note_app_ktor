package com.example.mynotes.data.local.db.mapper

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.ui.extensions.emptyString

val SIMPLE_RESPONSE_TO_DATA_MAPPER = object : Mapper<SimpleResponse, SimpleData> {
    override fun map(source: SimpleResponse): SimpleData = SimpleData(
        source.successful ?: false,
        source.message ?: emptyString()
    )
}