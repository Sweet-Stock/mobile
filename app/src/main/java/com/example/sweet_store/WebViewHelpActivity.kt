package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sweet_store.databinding.ActivityWebViewHelpBinding

class WebViewHelpActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var binding: ActivityWebViewHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webview
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("https://app.pipefy.com/public/form/XipVRTSo")
                return true
            }
        }
        webView.loadUrl("https://app.pipefy.com/public/form/XipVRTSo")
    }
}