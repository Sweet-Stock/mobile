package com.example.sweet_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    fun goToPageSignUp(view: View) {
        val signUpPage = Intent(this, SingUpActivity::class.java)
        startActivity(signUpPage)
    }
}