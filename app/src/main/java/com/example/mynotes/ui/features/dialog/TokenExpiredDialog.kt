package com.example.mynotes.ui.features.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mynotes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TokenExpiredDialog : DialogFragment() {
    var positiveListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_log_out)
            .setTitle(getString(R.string.token_expired_dialog_title))
            .setMessage(getString(R.string.token_expired_dialog_message))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                positiveListener?.invoke()
                dismiss()
            }
            .create()
    }
}