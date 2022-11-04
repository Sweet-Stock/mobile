package com.example.sweet_store.confectionery_rating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityConfectioneryProfileBinding
import com.example.sweet_store.databinding.ActivityPaymentMethodBinding
import com.example.sweet_store.page_controll.navigation.FooterBar
import com.example.sweet_store.page_controll.navigation.NavigationHeader
import com.example.sweet_store.page_controll.navigation.NavigationHeaderV2
import com.example.sweet_store.profile.ProfileBox

class ConfectioneryRating : AppCompatActivity() {
    private lateinit var binding: ActivityConfectioneryProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confectionery_rating)
        binding =ActivityConfectioneryProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val transition = supportFragmentManager.beginTransaction()


        val argumentsHeader = Bundle()
        argumentsHeader.putString("backButton","")
        argumentsHeader.putString("middleContent","Avalie")

        val argumentsCommentBox = Bundle()
        argumentsCommentBox.putString("name","Le Docê")
        val argumentsFooterBar = Bundle()

        val fragmentHeader = FragmentContainerView(applicationContext)
        val fragmentConfectioneryBox = FragmentContainerView(applicationContext)
        val fragmentFooterBar = FragmentContainerView(applicationContext)

        fragmentHeader.id = View.generateViewId()
        fragmentConfectioneryBox.id = View.generateViewId()
        fragmentFooterBar.id = View.generateViewId()

        binding.containerHeader.addView(fragmentHeader)
        binding.containerCommentBox.addView(fragmentConfectioneryBox)
        binding.containerFooterBar.addView(fragmentFooterBar)

        transition.add(fragmentHeader.id, NavigationHeader::class.java, argumentsHeader)
        transition.add(fragmentConfectioneryBox.id, ConfectioneryRatingBox::class.java, argumentsCommentBox)
        transition.add(fragmentFooterBar.id, FooterBar::class.java, argumentsFooterBar)
        transition.commit()
    }



}