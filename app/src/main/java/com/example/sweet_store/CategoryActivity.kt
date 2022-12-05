package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.databinding.ActivityCategoryBinding
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import com.example.sweet_store.ui.confectionery.ConfectioneryVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {
    private val retrofit = Rest.getInstanceSweetStock()
    private lateinit var binding: ActivityCategoryBinding
    lateinit var newArrayList: ArrayList<ConfectioneryVO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newArrayList = arrayListOf<ConfectioneryVO>()

        this.binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.confectioneryRecyclerContainer
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CategoryAdapter(ArrayList())
        callService(recyclerView, "")

    }


    private fun callService(recyclerView: RecyclerView, category: String) {

        val request = retrofit.create(Company::class.java).getConfectioneryByCategory(category)
        request.enqueue(object : Callback<List<ConfectioneryVO>> {
            override fun onResponse(
                call: Call<List<ConfectioneryVO>>,
                response: Response<List<ConfectioneryVO>>
            ) {

                if (response.code() == 200) {
                    response.body()!!.forEach {
                        newArrayList.add(it)
                        println("Teste   " + newArrayList)
                    }
                    recyclerView.adapter = CategoryAdapter(newArrayList)
                }
            }

            override fun onFailure(call: Call<List<ConfectioneryVO>>, t: Throwable) {
                Toast.makeText(baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }

}