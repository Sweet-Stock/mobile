package com.example.sweet_store.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.R
import com.example.sweet_store.confectionery.ConfectioneryBox
import com.example.sweet_store.databinding.ActivityConfectioneryBinding
import com.example.sweet_store.databinding.ActivityProfileBinding
import com.example.sweet_store.page_controll.navigation.FooterBar
import com.example.sweet_store.page_controll.navigation.NavigationHeader
import com.example.sweet_store.page_controll.navigation.NavigationHeaderV2

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transition = supportFragmentManager.beginTransaction()


        val argumentsHeader = Bundle()
        argumentsHeader.putString("backButton","")
        argumentsHeader.putString("middleContent","Perfil")

        val argumentsCommentBox = Bundle()
        argumentsCommentBox.putString("picture","")

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

        transition.add(fragmentHeader.id, NavigationHeaderV2::class.java, argumentsHeader)
        transition.add(fragmentConfectioneryBox.id, ProfileBox::class.java, argumentsCommentBox)
        transition.add(fragmentFooterBar.id, FooterBar::class.java, argumentsFooterBar)
        transition.commit()
    }
}