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
import com.example.sweet_store.AboutUsActivity
import com.example.sweet_store.R
import com.example.sweet_store.databinding.FragmentHomeBinding
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import com.example.sweet_store.ui.confectionery.ConfectioneryAdapter
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
        val recyclerView = binding.confectioneryRecyclerContainer
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<ConfectioneryVO>()
        return binding.root
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