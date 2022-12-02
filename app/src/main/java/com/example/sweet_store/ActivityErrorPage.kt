package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sweet_store.databinding.ActivityErroPageBinding

class ActivityErrorPage : AppCompatActivity() {
    private lateinit var binding: ActivityErroPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErroPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val error = intent.getStringExtra("error")
        val textViewError: TextView = binding.textError
        textViewError.text = error
    }
}