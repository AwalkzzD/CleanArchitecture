package com.example.cleanarchitecture.presentation.ui.home.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.base.views.BaseFragment
import com.example.cleanarchitecture.databinding.FragmentWebViewBinding

class WebViewFragment : BaseFragment<FragmentWebViewBinding, WebViewViewModel>(
    R.layout.fragment_web_view,
    WebViewViewModel::class.java
) {
    override fun setUpView() {

        fragmentBinding.webView.settings.javaScriptEnabled = true

        fragmentBinding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        fragmentBinding.webView.loadUrl("https://rohanpansara.github.io/PunchHub/")
    }
}