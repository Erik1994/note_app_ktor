package com.example.mynotes.ui.features.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNotesBinding
import com.example.mynotes.ui.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment: BaseFragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    override val viewModel: NotesViewModel by viewModel()

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