package com.eqra.labmid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eqra.labmid.databinding.ActivityWebViewBinding
import android.webkit.WebViewClient

import android.R
import android.view.View


class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeWebView("http://www.google.com")
    }

    private fun initializeWebView(url: String) {

        binding.apply {
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)
        }
    }
}