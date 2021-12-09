package com.example.mynotes.ui.features.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNotesBinding
import com.example.mynotes.ui.common.BaseFragment

class NotesFragment: BaseFragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    override val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}