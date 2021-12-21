package com.example.mynotes.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynotes.data.repository.util.EventManager
import com.example.mynotes.data.repository.util.Events
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import com.example.mynotes.ui.extensions.emptyString
import com.example.mynotes.ui.extensions.showSnackbar
import com.example.mynotes.ui.features.auth.AuthFragmentDirections
import com.example.mynotes.ui.features.notes.NotesFragmentDirections
import com.example.mynotes.ui.navigation.NavigationCommand
import org.koin.android.ext.android.inject

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected abstract val viewModel: BaseViewModel
    private val eventManager: EventManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
    }

    private fun observeNavigation() {
        collectLifeCycleFlow(viewModel.navigationFlow) {
            when (it) {
                NavigationCommand.Back -> findNavController().navigateUp()
                is NavigationCommand.To -> findNavController().navigate(it.direction)
                is NavigationCommand.None -> Unit
            }
        }

        collectLifeCycleFlow(eventManager.event) { event ->
            when (event) {
                is Events.LogOutEvent -> logOut(event.message)
            }
        }
    }

    protected fun logOut(eventMessage: String = emptyString()) = eventMessage.takeIf { it.isNotEmpty() }?.let {
        showSnackbar(it)
        viewModel.navigate(AuthFragmentDirections.actionGlobalAuthFragment())
    } ?: viewModel.navigate(NotesFragmentDirections.actionNotesFragmentToAuthFragment())
}