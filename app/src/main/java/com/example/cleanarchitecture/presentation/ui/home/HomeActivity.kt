package com.example.cleanarchitecture.presentation.ui.home

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityHomeBinding
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseActivity
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, BaseViewModel>(
    R.layout.activity_home,
    BaseViewModel::class.java
)