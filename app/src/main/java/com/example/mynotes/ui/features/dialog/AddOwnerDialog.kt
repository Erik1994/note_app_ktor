package com.example.mynotes.ui.features.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mynotes.R
import com.example.mynotes.ui.features.notedetail.NoteDetailViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddOwnerDialog : DialogFragment() {
    private val viewModel: NoteDetailViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val addOwnerEditText = activity?.layoutInflater?.inflate(R.layout.edit_text_email, null) as TextInputLayout
        return MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_add_person)
            .setTitle(getString(R.string.add_owner_dialog_title))
            .setMessage(getString(R.string.add_owner_dialog_message))
            .setView(addOwnerEditText)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                val email = addOwnerEditText.findViewById<EditText>(R.id.etAddOwnerEmail).text.toString()
                viewModel.setEmail(email)
            }
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}