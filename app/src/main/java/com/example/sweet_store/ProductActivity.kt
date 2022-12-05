package com.example.sweet_store

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweet_store.databinding.ActivityProductBinding
import com.example.sweet_store.model.request.AddProductCart
import com.example.sweet_store.model.response.AddProductCartResponse
import com.example.sweet_store.model.response.ProductVO
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstanceSweetStock()
    private lateinit var binding: ActivityProductBinding
    lateinit var product: ProductVO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uuid: String = intent.getStringExtra("idProduct") ?: ""
        val uuidConfectionery: String = intent.getStringExtra("idConfectionery") ?: ""
        val prefs = baseContext?.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val uuidUser = prefs?.getString("userId", "") ?: ""

        binding.btAdd.setOnClickListener {
            callServiceAddProduct(uuidUser, uuid, uuidConfectionery)
        }
        callServiceProduct(uuid)
    }

    private fun formatBase64(code: String): String {
        var formattedString = code

        formattedString = formattedString.replace("data:image/png;base64,", "")
        formattedString = formattedString.replace("data:image/svg;base64,", "")
        formattedString = formattedString.replace("data:image/svg+xml;base64,", "")
        formattedString = formattedString.replace("data:image/jpeg;base64,", "")
        formattedString = formattedString.replace("data:image/jpg;base64,", "")
        return formattedString
    }

    fun convertStringToBitmap(base64Str: String?): Bitmap? {
        val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    private fun callServiceAddProduct(uuidUser: String, uuidProduct: String, uuidCompany: String) {

        val body = AddProductCart(uuidProduct, uuidCompany, 1)
        val request = retrofit.create(Product::class.java).addCart(body, uuidUser)
        request.enqueue(object : Callback<AddProductCartResponse> {
            override fun onResponse(
                call: Call<AddProductCartResponse>,
                response: Response<AddProductCartResponse>
            ) {
                   Toast.makeText(baseContext, "Produto adicionado com sucesso!!", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<AddProductCartResponse>, t: Throwable) {
                 Toast.makeText(baseContext, "Falha ao adicionar produto!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun callServiceProduct(uuid: String) {
        val request = retrofit.create(Product::class.java).getProductById(uuid)
        request.enqueue(object : Callback<ProductVO> {
            override fun onResponse(
                call: Call<ProductVO>,
                response: Response<ProductVO>
            ) {
                if (response.code() == 200) {
                    product = response.body()!!
                    print(product)
                    binding.nameProduct.text = product.name;
                    binding.titlePage.text = product.name;
                    if (!product.picture.isNullOrEmpty()) {
                        val bruteBase64 = product.picture

                        val base64 = formatBase64(bruteBase64)
                        binding.imageProduct.setImageBitmap(convertStringToBitmap(base64))
                    }

                    binding.valueProduct.text = product.saleValue
                    binding.calories.text = product.nutritionalFacts.calories.toString()
                    binding.sodium.text = product.nutritionalFacts.sodium.toString()
                    binding.sugars.text = product.nutritionalFacts.sugars.toString()
                    binding.protein.text = product.nutritionalFacts.protein.toString()
                    binding.fat.text = product.nutritionalFacts.fat.toString()
                }
            }

            override fun onFailure(call: Call<ProductVO>, t: Throwable) {
                //      Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun addCart(view: View) {

    }

}