package com.example.sweet_store

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.confectionery.Confectionery
import com.example.sweet_store.confectionery_rating.ConfectioneryRating

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,  HomeActivity::class.java))
    }

}