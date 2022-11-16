package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sweet_store.databinding.ActivityWebViewHelpBinding
import com.example.sweet_store.databinding.ActivityWebViewSiteBinding

class WebViewSiteActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var binding: ActivityWebViewSiteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_site)

        binding = ActivityWebViewSiteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webview
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("https://sweet-stock.servehttp.com/")
                return true
            }
        }
        webView.loadUrl("https://sweet-stock.servehttp.com/")
    }
}