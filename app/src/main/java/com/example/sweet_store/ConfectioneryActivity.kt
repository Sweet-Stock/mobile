package com.example.sweet_store

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.ListAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.databinding.ActivityConfectioneryBinding
import com.example.sweet_store.model.response.ProductVO
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import com.example.sweet_store.service.Product
import com.example.sweet_store.ui.confectionery.ConfectioneryAdapter
import com.example.sweet_store.ui.confectionery.ConfectioneryVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfectioneryActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstanceSweetStock()
    private lateinit var binding: ActivityConfectioneryBinding
    lateinit var confectionery: ConfectioneryVO
     var newArrayList: ArrayList<ProductVO>  = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newArrayList = arrayListOf<ProductVO>()

        this.binding = ActivityConfectioneryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uuid: String? = intent.getStringExtra("idConfectionery")
        callServiceConfectionery(uuid!!)
        val recyclerView = binding.productRecyclerContainer
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ProductAdapter(ArrayList())
        callService(recyclerView, uuid)

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

    private fun callService(recyclerView: RecyclerView, uuid: String) {

        val request = retrofit.create(Product::class.java).getProducts(uuid)
        request.enqueue(object : Callback<List<ProductVO>> {
            override fun onResponse(
                call: Call<List<ProductVO>>,
                response: Response<List<ProductVO>>
            ) {

                if (response.code() == 200) {
                    response.body()!!.forEach {
                        newArrayList.add(it)
                        println("aaaaaaaaaaa   " + newArrayList)
                    }
                    recyclerView.adapter = ProductAdapter(newArrayList)
                }
            }

            override fun onFailure(call: Call<List<ProductVO>>, t: Throwable) {
       //         Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
                println(t.message)
            }

        })
    }

    private fun callServiceConfectionery(uuid: String) {

        val request = retrofit.create(Company::class.java).getConfectioneryById(uuid)
        request.enqueue(object : Callback<ConfectioneryVO> {
            override fun onResponse(
                call: Call<ConfectioneryVO>,
                response: Response<ConfectioneryVO>
            ) {
                if (response.code() == 200) {
                   confectionery = response.body()!!
                    print(confectionery)
                   val street = confectionery.address.street ?: ""


                    if  (!confectionery.picture.isNullOrEmpty()){
                        val bruteBase64 = confectionery.picture

                        val base64 = formatBase64(bruteBase64)
                        binding.imageConfectionery.setImageBitmap(convertStringToBitmap(base64))
                    }

                    binding.nameConfectionery.text = confectionery.fantasyName;
                    binding.titlePage.text = confectionery.fantasyName;
                    binding.adressConfectionery.text = street
                }
            }
            override fun onFailure(call: Call<ConfectioneryVO>, t: Throwable) {
          //      Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }
}