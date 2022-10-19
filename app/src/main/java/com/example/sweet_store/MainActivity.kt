package com.example.sweet_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sweet_store.confectionery_profile.ConfectioneryProfile
import com.example.sweet_store.page_controll.rating.RatingPage
import com.example.sweet_store.profile.Profile

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,  Profile::class.java))
    }

}