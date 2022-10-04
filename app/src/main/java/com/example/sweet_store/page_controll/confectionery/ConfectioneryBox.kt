package com.example.sweet_store.page_controll.confectionery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sweet_store.R

class ConfectioneryBox:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confectionery_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //      val backButton = arguments?.getString("backButton").toString()
        val picture = arguments?.getString("picture").toString()
        val name = arguments?.getString("name").toString()
        val stars = arguments?.getString("star").toString()
        val endereco = arguments?.getString("endereco").toString()
//        val pictureScr = arguments?.getParcelable<Bitmap>("pictureScr")


        view.findViewById<TextView>(R.id.profileName).text = name
        view.findViewById<TextView>(R.id.confectionaryRating).text = stars
        view.findViewById<TextView>(R.id.locationName).text = endereco


    }
}