package com.example.mynotes.ui.features.notes

import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.databinding.FragmentNotesBinding
import com.example.mynotes.ui.common.BaseFragment
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import com.example.mynotes.ui.extensions.debounceClicks
import com.example.mynotes.ui.extensions.showSnackbar
import com.example.mynotes.ui.features.notes.adapter.NotesAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : BaseFragment(R.layout.fragment_notes) {
    private var binding: FragmentNotesBinding? = null
    override val viewModel: NotesViewModel by viewModel()
    private val notesAdapter: NotesAdapter = get()
    private val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                binding?.swipeRefreshLayout?.isEnabled = !isCurrentlyActive
            }
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val note = notesAdapter.currentList[position]
            viewModel.deleteNoteById(note.id)
            showSnackbar(
                getString(R.string.note_deleted_message), getString(R.string.undo)
            ) {
                viewModel.addNote(note)
                viewModel.deleteLocallyDeletedNoteId(note.id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        if (binding == null) {
            binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        observeData()
        observeClicks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_notes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                viewModel.deleteToken()
                logOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeData() {
        binding?.apply {
            collectLifeCycleFlow(viewModel.notesSharedFlow) { event ->
                val result = event.peekContent()
                when (result) {
                    is Resource.Error -> {
                        event.getContentIfNotHandled()?.let {
                            result.message?.let {
                                showSnackbar(it)
                            }
                        }
                        result.data?.let {
                            notesAdapter.submitList(it)
                        }
                        swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Success -> {
                        notesAdapter.submitList(result.data)
                        swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Loading -> {
                        result.data?.let {
                            notesAdapter.submitList(it)
                        }
                        swipeRefreshLayout.isRefreshing = true
                    }
                }
            }
        }
    }

    @FlowPreview
    private fun observeClicks() {
        binding?.apply {
            fabAddNote.debounceClicks()
                .onEach {
                    viewModel.navigate(
                        NotesFragmentDirections.actionNotesFragmentToAddEditNoteFragment(
                            ""
                        )
                    )
                }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        notesAdapter.clicksFlow()
            .onEach {
                viewModel.navigate(
                    NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(
                        it.id
                    )
                )
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initRv() {
        binding?.rvNotes?.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
            ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(this)
        }
    }
}