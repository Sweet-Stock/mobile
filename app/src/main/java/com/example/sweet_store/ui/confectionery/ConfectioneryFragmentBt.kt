package com.example.sweet_store.ui.confectionery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet_store.databinding.FragmentConfectioneryBtBinding
import com.example.sweet_store.ui.home.HomeAdapter

class ConfectioneryFragmentBt : Fragment() {
    private var _binding: FragmentConfectioneryBtBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ConfectioneryViewModel::class.java)

        _binding = FragmentConfectioneryBtBinding.inflate(inflater, container, false)
        val root: View = binding.root



        loadRecyclerView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadRecyclerView() {
        val recyclerContainer = binding.confectioneryRecyclerContainer
        recyclerContainer.layoutManager = LinearLayoutManager(context)
        recyclerContainer.adapter = ConfectioneryAdapter()
    }
}