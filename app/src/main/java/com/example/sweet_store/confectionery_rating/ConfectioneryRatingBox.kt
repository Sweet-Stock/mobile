package com.example.sweet_store.confectionery_rating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sweet_store.R


class ConfectioneryRatingBox : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confectionery_rating_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val confectioneryName = arguments?.getString("name").toString()


        view.findViewById<TextView>(R.id.confectionery_name).text = confectioneryName
        view.findViewById<TextView>(R.id.confectionery_name2).text = confectioneryName
    }
}