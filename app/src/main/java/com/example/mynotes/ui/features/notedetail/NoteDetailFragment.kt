package com.example.mynotes.ui.features.notedetail

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.mynotes.R
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.databinding.FragmentNoteDetailBinding
import com.example.mynotes.ui.ADD_OWNER_DIALOG_TAG
import com.example.mynotes.ui.common.BaseFragment
import com.example.mynotes.ui.extensions.*
import com.example.mynotes.ui.features.dialog.AddOwnerDialog
import io.noties.markwon.Markwon
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment : BaseFragment(R.layout.fragment_note_detail) {
    private var binding: FragmentNoteDetailBinding? = null
    private val args: NoteDetailFragmentArgs by navArgs()
    override val viewModel: NoteDetailViewModel by sharedViewModel()
    private var currentNote: NoteEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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

        collectLifeCycleFlow(viewModel.emailStateFlow) { email ->
            email?.let {
                addOwnerToNode(it)
            }
        }

        collectLifeCycleFlow(viewModel.addOwnerSharedFlow) { event ->
            event.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is Resource.Error -> {
                        binding?.addOwnerProgressBar?.hide()
                        showSnackbar(result.message ?: getString(R.string.unknown_error))
                    }
                    is Resource.Success -> {
                        binding?.addOwnerProgressBar?.hide()
                        showSnackbar(result.data.message.takeIf { it.isNotEmpty() }
                            ?: getString(R.string.add_owner_success_message))
                    }
                    is Resource.Loading -> {
                        binding?.addOwnerProgressBar?.show()
                    }
                }
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

    private fun addOwnerToNode(email: String) {
        val addOwnerRequest = AddOwnerRequest(email, currentNote?.id ?: emptyString())
        viewModel.addOwnerToNote(addOwnerRequest)
    }

    private fun showAddOwnerDialog() {
        AddOwnerDialog().show(parentFragmentManager, ADD_OWNER_DIALOG_TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add_owner, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.addOwner -> showAddOwnerDialog()
        }
        return super.onOptionsItemSelected(item)
    }
}