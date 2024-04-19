package com.example.cleanarchitecture.base.views

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.cleanarchitecture.base.extensions.obtainViewModel
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.presentation.ui.home.users.UsersViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int, private val viewModelClass: Class<VM>
) : AppCompatActivity() {

    private lateinit var binding: VB
    private lateinit var viewModel: VM

    private var toolbar: Toolbar? = null

    /**
     * protected variable to use them in subclasses inheriting BaseActivity
     **/
    protected val activityBinding: VB get() = binding
    protected val activityViewModel: VM get() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)

        viewModel = getViewModel()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    @Suppress("UNCHECKED_CAST")
    fun getViewModel(): VM {
        if (!::viewModel.isInitialized) {
            viewModel = obtainViewModel(UsersViewModel::class.java) as VM
        }
        return viewModel
    }

    fun setupToolbar(toolbar: Toolbar, title: String, backButtonEnabled: Boolean = true) {
        this.toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(backButtonEnabled)
            setTitle(title)
        }
    }

    fun customiseToolbar(title: String, backButtonEnabled: Boolean = true) {
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(backButtonEnabled)
            setTitle(title)
        }
    }

    fun showToast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

}