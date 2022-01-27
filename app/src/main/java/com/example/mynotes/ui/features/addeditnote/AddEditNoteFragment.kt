package com.example.mynotes.ui.features.addeditnote

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mynotes.R
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.databinding.FragmentAddEditNoteBinding
import com.example.mynotes.ui.COLOR_PICKER_DIALOG_TAG
import com.example.mynotes.ui.DEFAULT_NOTE_COLOR
import com.example.mynotes.ui.common.BaseFragment
import com.example.mynotes.ui.extensions.changeDrawableColor
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import com.example.mynotes.ui.extensions.debounceClicks
import com.example.mynotes.ui.extensions.showSnackbar
import com.example.mynotes.ui.features.dialog.ColorPickerDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class AddEditNoteFragment : BaseFragment(R.layout.fragment_add_edit_note) {
    private var binding: FragmentAddEditNoteBinding? = null
    override val viewModel: AddEditNoteViewModel by sharedViewModel()
    private val args: AddEditNoteFragmentArgs by navArgs()
    private var currentNote: NoteEntity? = null
    private var currentNoteColor = DEFAULT_NOTE_COLOR

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        if (binding == null) {
            binding = FragmentAddEditNoteBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onPause() {
        super.onPause()
        //saveNote()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.id.isNotEmpty()) {
            viewModel.getNoteById(args.id)
        }
        observeData()
        observeClicks()
    }

    private fun changeNoteColor(color: String) {
        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.circle_shape, null)
        drawable?.let {
            binding?.viewNoteColor?.background =
                it.changeDrawableColor(Color.parseColor("#${color}"))
            currentNoteColor = color
        }
    }

    private fun observeData() {
        collectLifeCycleFlow(viewModel.noteSharedFlow) {
            it.getContentIfNotHandled()?.let { result ->
                when(result) {
                    is Resource.Error -> {
                        showSnackbar(result.message ?: getString(R.string.note_not_found))
                    }
                    is Resource.Success -> {
                        val note = result.data
                        currentNote = note
                        binding?.let { binding ->
                            with(binding) {
                                etNoteTitle.setText(note.title)
                                etNoteContent.setText(note.content)
                                changeNoteColor(note.color)
                            }
                        }

                    }
                    is Resource.Loading -> {
                        /* NO-OP */
                    }
                }

            }
        }
        collectLifeCycleFlow(viewModel.colorSharedFlow) { color ->
            color?.let {
                changeNoteColor(it)
            }
        }
    }

    @FlowPreview
    private fun observeClicks() = binding?.let {
        with(it) {
            viewNoteColor.debounceClicks()
                .onEach {
                    ColorPickerDialogFragment().show(parentFragmentManager, COLOR_PICKER_DIALOG_TAG)
                }.flowOn(Dispatchers.Main.immediate)
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun saveNote() = binding?.let let@{ binding ->
        with(binding) {
            val email = viewModel.getEmail()
            val title = etNoteTitle.text.toString()
            val content = etNoteContent.text.toString()
            if (title.isEmpty() || content.isEmpty()) {
                return@let
            }
            val date = System.currentTimeMillis()
            val color = currentNoteColor
            val id = currentNote?.id ?: UUID.randomUUID().toString()
            val owners = currentNote?.owners ?: listOf(email)
            val note = NoteRequest(title, content, date, owners, color, id)
            viewModel.addNote(note)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_apply, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.save -> {
                saveNote()
                //viewModel.navigate(AddEditNoteFragmentDirections.actionAddEditNoteFragmentToNotesFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}