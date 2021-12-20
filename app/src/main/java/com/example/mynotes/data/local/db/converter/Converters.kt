package com.example.mynotes.data.local.db.converter

import androidx.room.TypeConverter
import com.example.mynotes.ui.extensions.fromJson
import com.example.mynotes.ui.extensions.toJson

class Converters {

    @TypeConverter
    fun fromList(list: List<String>): String = list.toJson()

    @TypeConverter
    fun toList(string: String): List<String> = string.fromJson()
}