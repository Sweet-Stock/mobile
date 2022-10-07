package com.example.sweet_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.sweet_store.confectionery.Confectionery
import com.example.sweet_store.databinding.ActivityLoginBinding
import com.example.sweet_store.databinding.ActivitySingUpBinding
import com.example.sweet_store.model.address.Address
import com.example.sweet_store.model.request.LoginRequest
import com.example.sweet_store.model.response.LoginResponse
import com.example.sweet_store.model.response.UserResponse
import com.example.sweet_store.model.user.UserRequest
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun goLogin(view: View) {
        var email = binding.etEmail
        var password = binding.etPassword
        binding.etPassword
        tryLogin(email, password)
    }

    private fun tryLogin(
        etEmail: EditText,
        etPassword: EditText
    ) {


        var email: String = etEmail.text.toString()
        var password: String = etPassword.text.toString()
        val body = LoginRequest(email, password)
        val request = retrofit.create(User::class.java)

        request.login(body).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginPage = Intent(this@LoginActivity, Confectionery::class.java)
                    startActivity(loginPage)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })


    }


    fun goToPageSignUp(view: View) {
        val signUpPage = Intent(this@LoginActivity, SingUpActivity::class.java)
        startActivity(signUpPage)
    }
}