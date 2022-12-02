package com.example.sweet_store.payments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.ActivityErrorPage
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityAddCreditCardBinding
import com.example.sweet_store.model.payment_method.PaymentRequest
import com.example.sweet_store.model.payment_method.PaymentResponse
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.PaymentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class AddCreditCard : AppCompatActivity() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityAddCreditCardBinding
    var userId = "568571f3-e07f-48e8-b891-c35510de98fe"

    private lateinit var spinnerBrand: Spinner
    private lateinit var spinnerMethod: Spinner
    private lateinit var etNameCard: EditText
    private lateinit var tvNameCreditCard: TextView
    private lateinit var etNumberCreditCard: EditText
    private lateinit var tvNumberCreditCard: TextView
    private lateinit var etExpirationDate: EditText
    private lateinit var tvExpirationDate: TextView
    private lateinit var etCvcCode: EditText
    private lateinit var creditCardBrand: ImageView
    private lateinit var brand: String
    private lateinit var method: String


    private var images = intArrayOf(
        R.drawable.flag_elo,
        R.drawable.flag_american_express,
        R.drawable.flag_visa,
        R.drawable.flag_mastercard
    )

    private var brands = mutableListOf("Elo", "American Express", "Visa", "MasterCard")
    private var methods = mutableListOf("Débito", "Crédito")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_credit_card)

        binding = ActivityAddCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinnerBrand = binding.addCreditCardSpinnerBrand
        spinnerMethod = binding.addCreditCardSpinnerMethodPayment
        etNameCard = binding.etNameCreditCard
        tvNameCreditCard = binding.tvNameCreditCard
        etNumberCreditCard = binding.etNumberCreditCard
        tvNumberCreditCard = binding.tvNumberCreditCard
        etExpirationDate = binding.etExpirationDate
        tvExpirationDate = binding.tvExpirationDate
        etCvcCode = binding.etCvcCode
        creditCardBrand = binding.creditCardBrand

        onChangeNameCreditCard(etNameCard, tvNameCreditCard)
        onChangeNumberCreditCard(etNumberCreditCard, tvNumberCreditCard)
        onChangeDateExpirationCreditCard(etExpirationDate, tvExpirationDate)
        setupSpinnerMethod(spinnerMethod)
        setupSpinnerBrand(spinnerBrand)
    }

    private fun onChangeDateExpirationCreditCard(
        etExpirationDate: EditText,
        tvExpirationDate: TextView
    ) {
        etExpirationDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(p0: Editable?) {
                validateExpirationDate(etExpirationDate, tvExpirationDate)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateExpirationDate(
        etExpirationDate: EditText,
        tvExpirationDate: TextView
    ) {
        if (etExpirationDate.text.length == 2) {
            var month = etExpirationDate.text.take(2).toString().toInt()
            if (month > 12) {
                Toast.makeText(this@AddCreditCard, "Mês Inválido", Toast.LENGTH_SHORT).show()
                cleanCvc(etExpirationDate, tvExpirationDate)
            }
        }

        if (etExpirationDate.text.length == 4) {
            if (etExpirationDate.text.contains("/")) cleanCvc(etExpirationDate, tvExpirationDate)
            else {
                var year = etExpirationDate.text.takeLast(2).toString().toInt()
                if (year <= LocalDate.now().year.toString().takeLast(2).toInt()) {
                    Toast.makeText(this@AddCreditCard, "Ano Inválido", Toast.LENGTH_SHORT).show()
                    etExpirationDate.setText("")
                    tvExpirationDate.text = ""
                }
            }
        }

        if (etExpirationDate.text.length == 4 && !etExpirationDate.text.contains("/")) {
            tvExpirationDate.text = etExpirationDate.text.insert(2, "/")
            etExpirationDate.text =
                etExpirationDate.text.insert(2, "/")
        }
    }

    private fun cleanCvc(
        etExpirationDate: EditText,
        tvExpirationDate: TextView
    ) {
        etExpirationDate.setText("")
        tvExpirationDate.text = ""
    }

    private fun onChangeNumberCreditCard(
        etNumberCreditCard: EditText,
        tvNumberCreditCard: TextView
    ) {
        etNumberCreditCard.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var text: String = etNumberCreditCard.text.toString()
                tvNumberCreditCard.text = formatNumberCreditCard(text)
            }

            private fun formatNumberCreditCard(s: String): String {
                var formattedString = ""
                if (s.length < 5) {
                    return s
                }
                if (s.length in 5..16) {
                    s.forEachIndexed { i, char ->
                        if (i == 4 || i == 8 || i == 12) formattedString += " "
                        formattedString += char
                    }
                }
                return formattedString
            }
        })
    }

    private fun onChangeNameCreditCard(etNameCard: EditText, tvNameCreditCard: TextView) {
        etNameCard.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                tvNameCreditCard.text = etNameCard.text.toString().uppercase()
            }

        })
    }

    private fun setupSpinnerMethod(spinnerMethod: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.PaymentMethod,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMethod.adapter = adapter
        }

        spinnerMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                method = methods[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun setupSpinnerBrand(spinnerBrand: Spinner) {
        spinnerBrand.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                creditCardBrand.setImageResource(images[position])
                brand = brands[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val customAdapter = SpinnerBrandAdapter(applicationContext, images)
        spinnerBrand.adapter = customAdapter
    }

    fun savePaymentMethod(view: View) {
        if (isInvalidPaymentData()) {
            Toast.makeText(this@AddCreditCard, "Dados de pagamento inválidos", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val request = retrofit.create(PaymentService::class.java)

        request.addPayment(generatePayment()).enqueue(object : Callback<PaymentResponse> {
            override fun onResponse(
                call: Call<PaymentResponse>,
                response: Response<PaymentResponse>
            ) {
                if (response.isSuccessful) {
                    val paymentMethods = Intent(this@AddCreditCard, PaymentMethod::class.java)
                    startActivity(paymentMethods)
                }
            }

            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                val errorPage = Intent(this@AddCreditCard, ActivityErrorPage::class.java)
                errorPage.putExtra("error", "Falha ao cadastrar forma de pagamento. Motivo: ${t.message}")
                startActivity(errorPage)
            }
        })

    }

    private fun isInvalidPaymentData(): Boolean {
        return etNumberCreditCard.text.length < 16 || etNameCard.text.length < 5 || etCvcCode.text.length < 3 ||
                etNameCard.text.contains("[0-9]".toRegex()) ||
                etNameCard.text.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

    private fun generatePayment(): PaymentRequest {

        return PaymentRequest(
            uuidUser = userId,
            cardNumber = etNumberCreditCard.text.toString(),
            expirationDate = etExpirationDate.text.toString(),
            cardHolderName = etNameCard.text.toString().trim(),
            cvcCode = etCvcCode.text.toString(),
            type = method,
            brand = brand,
            paymentName = "$brand - $method",
        )
    }
}