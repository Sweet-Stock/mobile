package com.example.sweet_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.databinding.ActivityRatingPageBinding

class RatingPage : AppCompatActivity() {
    private lateinit var binding: ActivityRatingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transition = supportFragmentManager.beginTransaction()

        val argumentos = Bundle()
        argumentos.putString("backButton","")
        argumentos.putString("middleContent","Avaliações")
        argumentos.putString("pictureScr","")


        val fragmento = FragmentContainerView(applicationContext)
        fragmento.id = View.generateViewId()

        binding.container.addView(fragmento)
        transition.add(fragmento.id, NavigationHeader::class.java, argumentos)
        transition.commit()
    }

}