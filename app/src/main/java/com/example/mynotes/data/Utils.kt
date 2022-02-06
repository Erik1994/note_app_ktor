package com.example.mynotes.data

import android.content.Context
import com.example.mynotes.R
import com.example.mynotes.ui.extensions.emptyString

fun emailPassValidation(
    context: Context,
    email: String,
    password: String,
    repeatetPassword: String = emptyString(),
    isRegistr: Boolean = true
): String = if (email.isEmpty() || password.isEmpty() || if (isRegistr) {
        repeatetPassword.isEmpty()
    } else false
) {
    context.getString(R.string.empty_field_message)
} else if (isRegistr && password != repeatetPassword) {
    context.getString(R.string.passwords_mismatch_message)
} else {
    emptyString()
}