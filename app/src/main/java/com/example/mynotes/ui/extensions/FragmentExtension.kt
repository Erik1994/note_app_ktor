package com.example.mynotes.ui.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(text: String) {
    Snackbar
        .make( this.requireActivity().findViewById(android.R.id.content),
            text, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackbar(text: String, actionTitle: String, actionListener: View.OnClickListener) {
    Snackbar
        .make( this.requireActivity().findViewById(android.R.id.content),
            text, Snackbar.LENGTH_SHORT)
        .setAction(actionTitle, actionListener)
        .show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}