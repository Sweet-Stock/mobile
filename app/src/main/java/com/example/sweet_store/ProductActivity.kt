package com.example.sweet_store

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.sweet_store.databinding.ActivityConfectioneryBinding
import com.example.sweet_store.databinding.ActivityProductBinding
import com.example.sweet_store.model.response.ProductVO
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import com.example.sweet_store.service.Product
import com.example.sweet_store.ui.confectionery.ConfectioneryVO
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
        val uuid: String? = intent.getStringExtra("idProduct")
        callServiceProduct(uuid!!)
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

                    val bruteBase64 = product.picture
                    val base64 = formatBase64(bruteBase64)
                    binding.nameProduct.text = product.name;
                    binding.titlePage.text = product.name;
                    binding.imageProduct.setImageBitmap(convertStringToBitmap(base64))
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
}