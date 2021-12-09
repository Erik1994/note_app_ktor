package com.example.mynotes.ui.features.addeditnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentAddEditNoteBinding
import com.example.mynotes.ui.common.BaseFragment

class AddEditNoteFragment: BaseFragment(R.layout.fragment_add_edit_note) {
    private var binding: FragmentAddEditNoteBinding? = null
    override val viewModel: AddEditNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = FragmentAddEditNoteBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}