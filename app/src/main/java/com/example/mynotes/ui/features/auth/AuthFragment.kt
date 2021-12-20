package com.example.mynotes.ui.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.mynotes.R
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.databinding.FragmentAuthBinding
import com.example.mynotes.ui.common.BaseFragment
import com.example.mynotes.ui.extensions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment(R.layout.fragment_auth) {
    private var binding: FragmentAuthBinding? = null
    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDate()
        observeClicks()
    }

    private fun observeDate() {
        binding?.apply {
            collectLifeCycleFlow(viewModel.registerSharedFlow) { result ->
                when (result) {
                    is Resource.Error -> {
                        registerProgressBar.hide()
                        showSnackbar(
                            result.message
                                ?: getString(R.string.registration_sccess_message)
                        )
                    }
                    is Resource.Success -> {
                        registerProgressBar.hide()
                        showSnackbar(result.data.message.takeIf { it.isNotEmpty() }
                            ?: getString(R.string.registration_sccess_message))
                    }
                    is Resource.Loading -> {
                        registerProgressBar.show()
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private fun observeClicks() {
        binding?.apply {
            btnRegister.debounceClicks()
                .onEach {
                    val email = etRegisterEmail.text.toString()
                    val password = etRegisterPassword.text.toString()
                    val confirmedPassword = etRegisterPasswordConfirm.text.toString()
                    viewModel.register(
                        email,
                        password,
                        confirmedPassword
                    )
                }.flowOn(Dispatchers.Main.immediate)
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}