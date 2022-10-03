package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.sweet_store.databinding.ActivityRatingPageBinding
import com.example.sweet_store.databinding.ActivitySingUpBinding

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    companion object {
        var progressCont = 1
    }

    fun nextStep(v: View) {
        val ivProgressBar: ImageView = binding.progressBar
        val tvName: TextView = binding.tvName
        val etName: EditText = binding.etName
        val tvEmail: TextView = binding.tvEmail
        val etEmail: EditText = binding.etEmail
        val tvPassword: TextView = binding.tvPassword
        val etPassword: EditText = binding.etPassword
        val tvProfilePicture: TextView = binding.tvProfilePicture
        val etProfilePicture: EditText = binding.etProfilePicture
        val tvProfileType: TextView = binding.tvProfileType
        val etProfileType: EditText = binding.etProfileType
        val tvPhone: TextView = binding.tvPhoneNumber
        val etPhone: EditText = binding.etPhoneNumber
        val tvCep: TextView = binding.tvCep
        val etCep: EditText = binding.etCep
        val tvStreet: TextView = binding.tvStreet
        val etStreet: EditText = binding.etStreet
        val tvNumber: TextView = binding.tvStreetNumber
        val etNumber: EditText = binding.etStreetNumber
        val tvComplement: TextView = binding.tvComplement
        val etComplement: EditText = binding.etComplement
        val btBackButton: Button = binding.backButton

        progressCont++

        this.verifyStep(
            ivProgressBar,
            tvName,
            etName,
            tvEmail,
            etEmail,
            tvPassword,
            etPassword,
            tvProfilePicture,
            etProfilePicture,
            tvProfileType,
            etProfileType,
            tvPhone,
            etPhone,
            tvCep,
            etCep,
            tvStreet,
            etStreet,
            tvNumber,
            etNumber,
            tvComplement,
            etComplement,
            btBackButton
        )
    }
    fun backStep(v: View) {
        val ivProgressBar: ImageView = binding.progressBar
        val tvName: TextView = binding.tvName
        val etName: EditText = binding.etName
        val tvEmail: TextView = binding.tvEmail
        val etEmail: EditText = binding.etEmail
        val tvPassword: TextView = binding.tvPassword
        val etPassword: EditText = binding.etPassword
        val tvProfilePicture: TextView = binding.tvProfilePicture
        val etProfilePicture: EditText = binding.etProfilePicture
        val tvProfileType: TextView = binding.tvProfileType
        val etProfileType: EditText = binding.etProfileType
        val tvPhone: TextView = binding.tvPhoneNumber
        val etPhone: EditText = binding.etPhoneNumber
        val tvCep: TextView = binding.tvCep
        val etCep: EditText = binding.etCep
        val tvStreet: TextView = binding.tvStreet
        val etStreet: EditText = binding.etStreet
        val tvNumber: TextView = binding.tvStreetNumber
        val etNumber: EditText = binding.etStreetNumber
        val tvComplement: TextView = binding.tvComplement
        val etComplement: EditText = binding.etComplement
        val btBackButton: Button = binding.backButton

        progressCont--

        this.verifyStep(
            ivProgressBar,
            tvName,
            etName,
            tvEmail,
            etEmail,
            tvPassword,
            etPassword,
            tvProfilePicture,
            etProfilePicture,
            tvProfileType,
            etProfileType,
            tvPhone,
            etPhone,
            tvCep,
            etCep,
            tvStreet,
            etStreet,
            tvNumber,
            etNumber,
            tvComplement,
            etComplement,
            btBackButton
        )
    }
    private fun trySignUp(){

    }
    private fun verifyStep(
        ivProgressBar: ImageView,
        tvName: TextView, etName: EditText,
        tvEmail: TextView, etEmail: EditText,
        tvPassword: TextView, etPassword: EditText,
        tvProfilePicture: TextView, etProfilePicture: EditText,
        tvProfileType: TextView, etProfileType: EditText,
        tvPhone: TextView, etPhone: EditText,
        tvCep: TextView, etCep: EditText,
        tvStreet: TextView, etStreet: EditText,
        tvNumber: TextView, etNumber: EditText,
        tvComplement: TextView, etComplement: EditText,
        btBackButton: Button
    ) {
        when (progressCont) {
            1 -> {
                ivProgressBar.setImageResource(R.drawable.progress_bar_1)

                tvName.visibility = View.VISIBLE
                etName.visibility = View.VISIBLE
                tvEmail.visibility = View.VISIBLE
                etEmail.visibility = View.VISIBLE
                etPassword.visibility = View.VISIBLE
                tvPassword.visibility = View.VISIBLE

                tvProfilePicture.visibility = View.GONE
                etProfilePicture.visibility = View.GONE
                tvProfileType.visibility = View.GONE
                etProfileType.visibility = View.GONE
                tvPhone.visibility = View.GONE
                etPhone.visibility = View.GONE

                tvCep.visibility = View.GONE
                etCep.visibility = View.GONE
                tvStreet.visibility = View.GONE
                etStreet.visibility = View.GONE
                tvNumber.visibility = View.GONE
                etNumber.visibility = View.GONE
                tvComplement.visibility = View.GONE
                etComplement.visibility = View.GONE

                btBackButton.visibility = View.GONE
            }
            2 -> {
                ivProgressBar.setImageResource(R.drawable.progress_bar_2)

                tvName.visibility = View.GONE
                etName.visibility = View.GONE
                tvEmail.visibility = View.GONE
                etEmail.visibility = View.GONE
                etPassword.visibility = View.GONE
                tvPassword.visibility = View.GONE

                tvProfilePicture.visibility = View.VISIBLE
                etProfilePicture.visibility = View.VISIBLE
                tvProfileType.visibility = View.VISIBLE
                etProfileType.visibility = View.VISIBLE
                tvPhone.visibility = View.VISIBLE
                etPhone.visibility = View.VISIBLE

                tvCep.visibility = View.GONE
                etCep.visibility = View.GONE
                tvStreet.visibility = View.GONE
                etStreet.visibility = View.GONE
                tvNumber.visibility = View.GONE
                etNumber.visibility = View.GONE
                tvComplement.visibility = View.GONE
                etComplement.visibility = View.GONE

                btBackButton.visibility = View.VISIBLE
            }
            3 -> {
                ivProgressBar.setImageResource(R.drawable.progress_bar_3)

                tvName.visibility = View.GONE
                etName.visibility = View.GONE
                tvEmail.visibility = View.GONE
                etEmail.visibility = View.GONE
                etPassword.visibility = View.GONE
                tvPassword.visibility = View.GONE

                tvProfilePicture.visibility = View.GONE
                etProfilePicture.visibility = View.GONE
                tvProfileType.visibility = View.GONE
                etProfileType.visibility = View.GONE
                tvPhone.visibility = View.GONE
                etPhone.visibility = View.GONE

                tvCep.visibility = View.VISIBLE
                etCep.visibility = View.VISIBLE
                tvStreet.visibility = View.VISIBLE
                etStreet.visibility = View.VISIBLE
                tvNumber.visibility = View.VISIBLE
                etNumber.visibility = View.VISIBLE
                tvComplement.visibility = View.VISIBLE
                etComplement.visibility = View.VISIBLE

                btBackButton.visibility = View.VISIBLE
            }
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }
}