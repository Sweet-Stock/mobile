package com.example.sweet_store.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.CategoryActivity
import com.example.sweet_store.cart.Cart
import com.example.sweet_store.databinding.FragmentHomeBinding
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import com.example.sweet_store.ui.confectionery.ConfectioneryFragmentBt
import com.example.sweet_store.ui.confectionery.ConfectioneryVO
import com.example.sweet_store.ui.confectionery.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    val retrofit = Rest.getInstanceSweetStock()

    lateinit var newArrayList: ArrayList<ConfectioneryVO>
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentB = ConfectioneryFragmentBt()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setGoToCartPage()
        val recyclerView = binding.confectioneryRecyclerContainer
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<ConfectioneryVO>()

        val img01 = binding.img01
        img01.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "CAKES")
            startActivity(intent)
        }

        val img02 = binding.img02
        img02.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "COOKIES")
            startActivity(intent)
        }

        val img03 = binding.img03
        img03.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "BRIGADEIROS")
            startActivity(intent)
        }
        val img04 = binding.img04
        img04.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "BROWNIES")
            startActivity(intent)
        }
        val img05 = binding.img05
        img05.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "CUPCAKES")
            startActivity(intent)
        }
        val img06 = binding.img06
        img06.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "FINE_SWEETS")
            startActivity(intent)
        }
        val img07 = binding.img07
        img07.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "DONUTS")
            startActivity(intent)
        }
        val img08 = binding.img08
        img08.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "MOUSSES")
            startActivity(intent)
        }
        val img09 = binding.img09
        img09.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "PUDDINGS")
            startActivity(intent)
        }
        val img10 = binding.img10
        img10.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "ICE_CREAMS")
            startActivity(intent)
        }
        val img11 = binding.img11
        img11.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "PIES")
            startActivity(intent)
        }
        val img12 = binding.img12
        img12.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "TRUFFLES")
            startActivity(intent)
        }
        val img13 = binding.img13
        img13.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "ZERO_SUGAR")
            startActivity(intent)
        }
        val img14 = binding.img14
        img14.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "ZERO_LACTOSE")
            startActivity(intent)
        }
        return binding.root
    }

    private fun setGoToCartPage() {
        binding.btnGoToCartPage.setOnClickListener {
            val intent = Intent(activity, Cart::class.java)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.confectioneryRecyclerContainer

        callService(recyclerView)
    }

    private fun callService(recyclerView: RecyclerView) {

        val request = retrofit.create(Company::class.java).getConfectionery()
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
                    recyclerView.adapter = HomeAdapter(newArrayList)
                }
            }

            override fun onFailure(call: Call<List<ConfectioneryVO>>, t: Throwable) {
                Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }
}