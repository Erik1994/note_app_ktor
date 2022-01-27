package com.example.mynotes.ui.features.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mynotes.R
import com.example.mynotes.ui.features.addeditnote.AddEditNoteViewModel
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ColorPickerDialogFragment : DialogFragment() {
    private val viewModel: AddEditNoteViewModel by sharedViewModel()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return ColorPickerDialog.Builder(requireContext())
            .setTitle(getString(R.string.choose_color))
            .setPositiveButton(getString(R.string.ok), object : ColorEnvelopeListener {
                override fun onColorSelected(envelope: ColorEnvelope?, fromUser: Boolean) {
                    envelope?.let {
                        viewModel.setColor(it.hexCode)
                    }
                }
            })
            .setNegativeButton(getString(R.string.cancel)) { dialogListener, _ ->
                dialogListener.cancel()
            }
            .setBottomSpace(12)
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .create()
    }
}