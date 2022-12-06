package com.example.sweet_store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.databinding.ActivityLoginBinding
import com.example.sweet_store.model.request.LoginRequest
import com.example.sweet_store.model.response.LoginResponse
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
        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun goLogin(view: View) {
        val email = binding.etEmail
        val password = binding.etPassword
        binding.etPassword
        tryLogin(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }

    private fun tryLogin(
        etEmail: EditText,
        etPassword: EditText
    ) {


        val email: String = etEmail.text.toString()
        val password: String = etPassword.text.toString()

        if (!isValidEmail(email)) {
            etEmail.error = "Email inválido :("
        }

        if (!isValidPassword(password)) {
            etPassword.error = "Senha inválida :("
        }

        if (isValidEmail(email) && isValidPassword(password)) {
            val body = LoginRequest(email, password)
            val request = retrofit.create(User::class.java)

            request.login(body).enqueue(
                object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.code() == 404) {
                            binding.etEmail.error = "Email não cadastrado, realize seu cadastro :) "
                        } else if (response.code() == 400) {
                            binding.etPassword.error = "Senha incorreta, tente novamente :) "
                        } else if (response.code() == 200) {
                            val loginPage = Intent(this@LoginActivity, HomeActivity::class.java)
                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
                            val editor = sharedPreference.edit()
                            editor.putString("userName", response.body()?.name)
                            editor.putString("userEmail", response.body()?.email)
                            editor.putString("userId",response.body()?.uuid)
                            editor.apply()

                            startActivity(loginPage)
                            println(response)
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        val errorPage = Intent(this@LoginActivity, ActivityErrorPage::class.java)
                        errorPage.putExtra("error", t.toString())
                        startActivity(errorPage)
                    }
                })
        }
    }

    fun goToPageSignUp(view: View) {
        val signUpPage = Intent(this@LoginActivity, SingUpActivity::class.java)
        startActivity(signUpPage)
    }
}