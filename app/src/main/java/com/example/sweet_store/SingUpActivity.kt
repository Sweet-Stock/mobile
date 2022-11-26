package com.example.sweet_store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.widget.*
import com.example.sweet_store.databinding.ActivitySingUpBinding
import com.example.sweet_store.enums.ProfileType
import com.example.sweet_store.model.address.Address
import com.example.sweet_store.model.response.UserResponse
import com.example.sweet_store.model.response.ViaCepResponse
import com.example.sweet_store.model.user.UserRequest
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.User
import com.example.sweet_store.ui.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {
    private val retrofit = Rest.getInstance()
    private val retrofitCep = Rest.getInstanceCep()
    private lateinit var binding: ActivitySingUpBinding
    private lateinit var profileType: String

    companion object {
        var progressCont = 1
        var address = Address()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etProfileType: Spinner = binding.spProfileType
        val profileTypes = resources.getStringArray(R.array.ProfileTypes)
        val profileTypeSpinner =
            ArrayAdapter(this, android.R.layout.select_dialog_item, profileTypes)

        etProfileType.adapter = profileTypeSpinner

        etProfileType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                profileType = getProfileType(profileTypes[position]).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val etCep: EditText = binding.etCep

        etCep.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length == 8) {
                    val request = retrofitCep.create(User::class.java)
                    request.getCep(s.toString()).enqueue(
                        object : Callback<ViaCepResponse> {
                            override fun onResponse(
                                call: Call<ViaCepResponse>,
                                response: Response<ViaCepResponse>
                            ) {
                                address.cep = response.body()?.cep
                                address.city = response.body()?.localidade
                                address.neighborhood = response.body()?.bairro
                                address.state = response.body()?.uf
                                address.street = response.body()?.logradouro
                                binding.etStreet.setText(response.body()?.logradouro)

                            }

                            override fun onFailure(call: Call<ViaCepResponse>, t: Throwable) {
                                Toast.makeText(this@SingUpActivity, "deu ruim", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )

                }
            }
        })


    }


    fun goToPageLogin(view: View) {
        val loginPage = Intent(this@SingUpActivity, LoginActivity::class.java)
        startActivity(loginPage)
    }

    fun goToPageTermsOfUse(view: View) {
        val termsOfUsePage = Intent(this@SingUpActivity, ActivityWebView::class.java)
        startActivity(termsOfUsePage)
    }

    private fun isValidCep(cep: String): Boolean {
        (cep.length < 8 || cep.length > 8)
        return false
    }

    private fun isValidPhone(phone: String): Boolean {
        (phone.length < 11)
        return false
    }


    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun isValidStreet(street: String): Boolean {
        (street.isEmpty())
        return false
    }

    private fun isValidNumber(number: String): Boolean {
        (number.isEmpty())
        return false
    }


    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }

    fun nextStep(v: View) {
        val ivProgressBar: ImageView = binding.progressBar
        val tvName: TextView = binding.tvName
        val etName: EditText = binding.etName
        val tvEmail: TextView = binding.tvEmail
        val etEmail: EditText = binding.etEmail
        val tvPassword: TextView = binding.tvPassword
        val etPassword: EditText = binding.etPassword
        val tvProfileType: TextView = binding.tvProfileType
        val etProfileType: Spinner = binding.spProfileType
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
        val btNextButton: Button = binding.nextButton

        if (progressCont == 1) {
            if (etName.text.toString().isEmpty()) {
                etName.error = "Insira um nome válido"
            }
            if (!isValidEmail(etEmail.text.toString())) {
                etEmail.error = "Insira um email válido"
            }
            if (!isValidPassword(etPassword.text.toString())) {
                etPassword.error = "Insira uma senha válida"
            }
            if (etName.text.toString()
                    .isNotEmpty() && isValidPassword(etPassword.text.toString()) && isValidEmail(
                    etEmail.text.toString()
                )
            ) {
                progressCont++
            }
        } else if (progressCont == 2) {
            if (etPhone.text.toString().isEmpty()) {
                etPhone.error = "Insira um número válido"
            }
            if (etPhone.text.toString().isNotEmpty()) {
                progressCont++
            }
        } else if (progressCont == 3) {
            if (etStreet.text.toString().isEmpty()) {
                etStreet.error = "Insira uma rua para continuar"
            }
            if (etNumber.text.toString().isEmpty()) {
                etNumber.error = "Insira um número para continuar"
            }
            if (etCep.text.toString().isEmpty()) {
                etCep.error = "Insira um CEP válido"
            }
            if (etNumber.text.toString().isNotEmpty() && etStreet.text.toString()
                    .isNotEmpty() && etCep.text.toString().isNotEmpty()
            ) {
                trySignUp(
                    etName, etEmail,
                    etPassword, etPhone,
                    etStreet, etCep,
                    etNumber, etComplement
                )
            }

        } else if (progressCont > 3) {
            return
        }

        this.verifyStep(
            ivProgressBar,
            tvName,
            etName,
            tvEmail,
            etEmail,
            tvPassword,
            etPassword,
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
            btBackButton, btNextButton
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
        val tvProfileType: TextView = binding.tvProfileType
        val etProfileType: Spinner = binding.spProfileType
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
        val btNextButton: Button = binding.nextButton
        if (progressCont > 0) progressCont--


        this.verifyStep(
            ivProgressBar,
            tvName,
            etName,
            tvEmail,
            etEmail,
            tvPassword,
            etPassword,
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
            btBackButton,
            btNextButton
        )
    }

    private fun trySignUp(
        etName: EditText, etEmail: EditText,
        etPassword: EditText, etPhone: EditText,
        etStreet: EditText, etCep: EditText,
        etNumber: EditText, etComplement: EditText,
    ) {

        var name: String = etName.text.toString()
        var email: String = etEmail.text.toString()
        var phone: String = etPhone.text.toString()
        //    var profileType: String = etProfileType.text.toString()
        var password: String = etPassword.text.toString()
        var street: String = etStreet.text.toString()
        var cep: String = etCep.text.toString()
        var number: String = etNumber.text.toString()
        var complement: String = etComplement.text.toString()
        address.number = number
        address.complement = complement
        address.street = street
        val body = UserRequest(name, email, "image", phone, this.profileType, password, address)
        val request = retrofit.create(User::class.java)

        request.register(body).enqueue(
            object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.code() == 400) {
                        binding.etEmail.error = "Email já cadastrado, realize seu login :) "
                    } else if (response.code() == 201) {
                        val loginPage = Intent(this@SingUpActivity, HomeFragment::class.java)
                        startActivity(loginPage)
                        println(response)
                    }

                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    val errorPage = Intent(this@SingUpActivity, activityErroPage::class.java)
                    errorPage.putExtra("error", t.toString())
                    startActivity(errorPage)
                }
            })


    }

    private fun verifyStep(
        ivProgressBar: ImageView,
        tvName: TextView, etName: EditText,
        tvEmail: TextView, etEmail: EditText,
        tvPassword: TextView, etPassword: EditText,
        tvProfileType: TextView, etProfileType: Spinner,
        tvPhone: TextView, etPhone: EditText,
        tvCep: TextView, etCep: EditText,
        tvStreet: TextView, etStreet: EditText,
        tvNumber: TextView, etNumber: EditText,
        tvComplement: TextView, etComplement: EditText,
        btBackButton: Button, btNextButton: Button

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
                btNextButton.text = "Cadastrar"
                btBackButton.visibility = View.VISIBLE
            }
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }

    private fun getProfileType(profileType: String): ProfileType? {
        return when (profileType) {
            "Moderado" -> ProfileType.MODERATE
            "Educação Alimentar" -> ProfileType.EDUCATION
            "Compulsivo" -> ProfileType.COMPULSIVE
            "Diabético" -> ProfileType.DIABETIC
            else -> null
        }
    }
}