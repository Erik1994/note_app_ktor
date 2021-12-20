package com.example.mynotes.ui.activity

import android.os.Bundle
import com.example.mynotes.R
import com.example.mynotes.ui.common.BaseActivity

class MainActivity : BaseActivity() {
    //private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}