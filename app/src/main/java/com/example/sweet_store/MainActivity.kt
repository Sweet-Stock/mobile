package com.example.sweet_store

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.payments.AddCreditCard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs = baseContext.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        var uuid = prefs?.getString("userId", "") ?: ""

        if (uuid.isNullOrEmpty()){
            startActivity(Intent(this, LoginActivity::class.java))

        }else{
            startActivity(Intent(this, HomeActivity::class.java))

        }
    }

}