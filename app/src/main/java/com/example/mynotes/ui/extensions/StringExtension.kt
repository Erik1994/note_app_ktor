package com.example.mynotes.ui.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun emptyString() = ""

inline fun <reified TYPE> String.fromJson(): TYPE =
    Gson().fromJson(this, object : TypeToken<TYPE>() {}.type)

inline fun <reified TYPE : Any> TYPE.toJson(): String =
    Gson().toJson(this)