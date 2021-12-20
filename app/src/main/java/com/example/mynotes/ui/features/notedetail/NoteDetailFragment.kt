package com.example.mynotes.ui.features.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentNoteDetailBinding
import com.example.mynotes.ui.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment: BaseFragment(R.layout.fragment_note_detail) {
    private var binding: FragmentNoteDetailBinding? = null
    override val viewModel: NoteDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = FragmentNoteDetailBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}