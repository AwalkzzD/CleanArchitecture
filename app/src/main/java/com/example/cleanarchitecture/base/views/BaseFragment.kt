package com.example.cleanarchitecture.base.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.base.viewmodel.ViewModelFactory

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int, private val viewModelClass: Class<VM>
) : Fragment() {

    private lateinit var binding: VB
    private lateinit var viewModel: VM

    protected val fragmentBinding: VB get() = binding
    protected val fragmentViewModel: VM get() = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel()
        setUpView()

    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModel(): VM {
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(
                requireActivity(), ViewModelFactory.getInstance(requireActivity().application)
            )[viewModelClass]
        }
        return viewModel
    }

    fun getActivityViewModel(): BaseViewModel {
        return ((activity as BaseActivity<*, *>).getViewModel())
    }

    fun showToast(message: String, duration: Int) {
        (activity as BaseActivity<*, *>).showToast(message, duration)
    }

    fun showDialog(
        titleText: String,
        messageText: String,
        positiveButtonText: String,
        negativeButtonText: String,
        onPositiveClick: () -> Unit
    ) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        dialogBuilder.setMessage(messageText).setCancelable(true)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                onPositiveClick()
                dialog.dismiss()
            }.setNegativeButton(negativeButtonText) { dialog, _ ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle(titleText)
        alert.show()
    }

    fun createProgressDialog(): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setView(R.layout.progress_dialog)
        dialogBuilder.setCancelable(false)
        return dialogBuilder.create()
            .apply { window?.setBackgroundDrawableResource(android.R.color.transparent) }
    }

    fun handleProgressDialog() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setView(R.layout.progress_dialog)
        dialogBuilder.setCancelable(false)
        val progressDialog = dialogBuilder.create()
            .apply { window?.setBackgroundDrawableResource(android.R.color.transparent) }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
    }

    open fun setUpView() = Unit

}