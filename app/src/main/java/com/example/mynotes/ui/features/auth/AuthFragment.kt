package com.example.mynotes.ui.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mynotes.R
import com.example.mynotes.databinding.FragmentAuthBinding
import com.example.mynotes.ui.common.BaseFragment

class AuthFragment: BaseFragment(R.layout.fragment_auth) {
    private var binding: FragmentAuthBinding? = null
    override val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}