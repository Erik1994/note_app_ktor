package com.example.mynotes.ui.features.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mynotes.R
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.databinding.FragmentNoteDetailBinding
import com.example.mynotes.ui.common.BaseFragment
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import io.noties.markwon.Markwon
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment : BaseFragment(R.layout.fragment_note_detail) {
    private var binding: FragmentNoteDetailBinding? = null
    private val args: NoteDetailFragmentArgs by navArgs()
    override val viewModel: NoteDetailViewModel by viewModel()
    private var currentNote: NoteEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentNoteDetailBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setClickListeners()
    }

    private fun setMarkdownText(text: String) {
        val markwon = Markwon.create(requireContext())
        val markdown = markwon.toMarkdown(text)
        binding?.let {
            markwon.setParsedMarkdown(it.tvNoteContent, markdown)
        }
    }

    private fun observeData() {
        collectLifeCycleFlow(viewModel.observeNoteById(args.id)) { note ->
            binding?.apply {
                tvNoteTitle.text = note.title
                setMarkdownText(note.content)
                currentNote = note
            }
        }
    }

    private fun setClickListeners() {
        binding?.apply {
            fabEditNote.setOnClickListener {
                viewModel.navigate(
                    NoteDetailFragmentDirections.actionNoteDetailFragmentToAddEditNoteFragment(
                        args.id
                    )
                )
            }
        }
    }
}