package com.example.sweet_store.ui.confectionery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.rest.Rest
import com.example.sweet_store.service.Company
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConfectioneryFragmentBt : Fragment() {

     val retrofit = Rest.getInstance()

     lateinit var recyclerView: RecyclerView
     lateinit var newArrayList: ArrayList<ConfectioneryVO>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.confectioneryRecyclerContainer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL

        recyclerView.setLayoutManager(llm)
        newArrayList = arrayListOf<ConfectioneryVO>()
        println("inicio   "+newArrayList)
        onResume()
    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    public fun callService() {

        val request = retrofit.create(Company::class.java).getConfectionery()
        request.enqueue(object : Callback<List<ConfectioneryVO>> {
            override fun onResponse(call: Call<List<ConfectioneryVO>>, response: Response<List<ConfectioneryVO>>) {

                if (response.code() == 200) {
                    newArrayList.clear()
                    response.body()!!.forEach { confectionery ->
                        newArrayList.add(confectionery)
                        println("Teste   "+newArrayList)
                    }
                    recyclerView.adapter = ConfectioneryAdapter(newArrayList)
                    getUserData()
                }
            }

            override fun onFailure(call: Call<List<ConfectioneryVO>>, t: Throwable) {
                Toast.makeText(activity?.baseContext, "Vish", Toast.LENGTH_SHORT).show()
            }

        })
    }

    public fun getUserData() {
        if(newArrayList.isEmpty()) {
            recyclerView.visibility = View.GONE
        } else {
            println(newArrayList)
            recyclerView.adapter = ConfectioneryAdapter(newArrayList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confectionery_bt, container, false)
    }

}