package com.example.mynotes.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynotes.ui.extensions.collectLifeCycleFlow
import com.example.mynotes.ui.navigation.NavigationCommand

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {
    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
    }

    private fun observeNavigation() {
        collectLifeCycleFlow(viewModel.navigationFlow) {
            when(it) {
                NavigationCommand.Back -> findNavController().navigateUp()
                is NavigationCommand.To -> findNavController().navigate(it.direction)
                is NavigationCommand.None -> Unit
            }
        }
    }
}