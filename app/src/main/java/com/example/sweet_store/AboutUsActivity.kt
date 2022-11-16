package com.example.sweet_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
    }

    fun goToPageTermsOfUse(view: View) {
        val termsOfUsePage = Intent(this@AboutUsActivity, ActivityWebView::class.java)
        startActivity(termsOfUsePage)
    }

    fun goToPageSite(view: View) {
        val site = Intent(this@AboutUsActivity, WebViewSiteActivity::class.java)
        startActivity(site)
    }
}