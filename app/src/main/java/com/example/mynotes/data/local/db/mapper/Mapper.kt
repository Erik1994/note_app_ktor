package com.example.mynotes.data.local.db.mapper

interface Mapper<SOURCE, RESULT> {

    fun map(source: SOURCE): RESULT

    fun map(source: List<SOURCE>): List<RESULT> = source.map { map(it) }

    fun mapIfNotNull(source: SOURCE?): RESULT? = source?.run { map(this) }

    fun mapIfNotNull(source: List<SOURCE>?): List<RESULT>? = source?.run { map(this) }
}

data class MapperDataHolder<out SOURCE> (val data: SOURCE)