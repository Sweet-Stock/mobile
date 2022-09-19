package com.example.sweet_store

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.sweet_store.databinding.ActivityRatingPageBinding
import com.example.sweet_store.databinding.FragmentNavigationHeaderBinding


class NavigationHeader : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = arguments?.getString("backButton").toString()
        val middleContent = arguments?.getString("middleContent").toString()
        val pictureScr = arguments?.getParcelable<Bitmap>("pictureScr")

        view.findViewById<TextView>(R.id.middleContentHeader).text = middleContent

    }

}