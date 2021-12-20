package com.example.mynotes.data

import android.content.Context
import com.example.mynotes.R

fun emailPassValidation(
    context: Context,
    email: String,
    password: String,
    repeatetPassword: String
): String = if(email.isEmpty() || password.isEmpty() || repeatetPassword.isEmpty()) {
    context.getString(R.string.empty_field_message)
} else if(password != repeatetPassword) {
    context.getString(R.string.passwords_mismatch_message)
} else {
    ""
}