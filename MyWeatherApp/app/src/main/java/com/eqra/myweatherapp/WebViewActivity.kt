package com.eqra.myweatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.eqra.myweatherapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webViewUrl = intent.getStringExtra("webView")

        if (webViewUrl != null) {
            initializeWebView(webViewUrl)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView(url: String) {

        binding.apply {
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)
        }
    }
}