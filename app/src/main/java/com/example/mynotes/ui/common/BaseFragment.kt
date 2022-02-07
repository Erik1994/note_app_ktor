package com.example.mynotes.ui.common

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynotes.data.repository.util.EventManager
import com.example.mynotes.data.repository.util.Events
import com.example.mynotes.ui.TOKEN_EXPIRED_DIALOG_TAG
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import com.example.mynotes.ui.features.auth.AuthFragmentDirections
import com.example.mynotes.ui.features.dialog.TokenExpiredDialog
import com.example.mynotes.ui.features.notes.NotesFragmentDirections
import com.example.mynotes.ui.navigation.NavigationCommand
import org.koin.android.ext.android.inject

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected abstract val viewModel: BaseViewModel
    private val eventManager: EventManager by inject()
    protected open var orientation: Int = SCREEN_ORIENTATION_USER
        set(value) {
            field = value
            requireActivity().requestedOrientation = value
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()

        // for screen rotation
        if (savedInstanceState != null) {
            val tokenExpiredDialog = parentFragmentManager.findFragmentByTag(
                TOKEN_EXPIRED_DIALOG_TAG
            ) as TokenExpiredDialog?
            tokenExpiredDialog?.positiveListener = {
                findNavController().navigate(AuthFragmentDirections.actionGlobalAuthFragment())
            }
        }
    }

    private fun observeNavigation() {
        collectLifeCycleFlow(viewModel.navigationFlow) { command ->
            command?.let {
                viewModel.setNavigationNull()
                when (it) {
                    is NavigationCommand.Back -> findNavController().navigateUp()
                    is NavigationCommand.To -> findNavController().navigate(it.direction)
                }
            }
        }

        collectLifeCycleFlow(eventManager.event) { event ->
            event?.let {
                eventManager.sendEvent(null)
                logOut(it)
            }
        }
    }

    protected fun logOut(events: Events = Events.LogOutEvent) {
        when (events) {
            is Events.LogOutEvent -> viewModel.navigate(NotesFragmentDirections.actionNotesFragmentToAuthFragment())
            is Events.TokenExpiredEvent -> showTokenExpiredDialog()
        }
    }

    private fun showTokenExpiredDialog() {
        TokenExpiredDialog().apply {
            positiveListener = {
                findNavController().navigate(AuthFragmentDirections.actionGlobalAuthFragment())
            }
        }.show(parentFragmentManager, TOKEN_EXPIRED_DIALOG_TAG)
    }
}