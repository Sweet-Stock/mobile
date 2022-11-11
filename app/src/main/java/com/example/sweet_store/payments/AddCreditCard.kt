package com.example.sweet_store.payments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityAddCreditCardBinding

class AddCreditCard : AppCompatActivity() {
    private lateinit var binding: ActivityAddCreditCardBinding

    private var images =
        intArrayOf(
            R.drawable.flag_elo,
            R.drawable.flag_american_express,
            R.drawable.flag_visa,
            R.drawable.flag_mastercard
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_credit_card)

        binding = ActivityAddCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerBrand = binding.addCreditCardSpinnerBrand
        val spinnerMethod = binding.addCreditCardSpinnerMethodPayment
        val etNameCard = binding.etNameCreditCard
        val tvNameCreditCard = binding.tvNameCreditCard
        val etNumberCreditCard = binding.etNumberCreditCard
        val tvNumberCreditCard = binding.tvNumberCreditCard
        val etExpirationDate = binding.etExpirationDate
        val tvExpirationDate = binding.tvExpirationDate

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
        etExpirationDate.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var text: String = etExpirationDate.text.toString()
//                etExpirationDate.setText(formatNumberExpirationDate(text))
                tvExpirationDate.text = formatNumberExpirationDate(text)
            }

            private fun formatNumberExpirationDate(s: String): String {
                var formattedString = ""
                if (s.length < 2) {
                    return s
                }
                if (s.length in 2..4) {
                    s.forEachIndexed { i, char ->
                        if (i == 2) formattedString += "/"
                        formattedString += char
                    }
                }
                return formattedString
            }

        })
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
                tvNameCreditCard.text = etNameCard.text
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

    }

    private fun setupSpinnerBrand(spinnerBrand: Spinner) {
        spinnerBrand.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val customAdapter = SpinnerBrandAdapter(applicationContext, images)
        spinnerBrand.adapter = customAdapter
    }
}