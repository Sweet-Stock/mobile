package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sweet_store.databinding.ActivitySingUpBinding
import com.example.sweet_store.databinding.ActivityWebViewBinding

class ActivityWebView : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webview
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("https://www.google.co.in/")
                return true
            }
        }
        webView.loadUrl("https://www.google.co.in/")
    }
}