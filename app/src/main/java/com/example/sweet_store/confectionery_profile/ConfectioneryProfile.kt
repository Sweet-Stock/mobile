package com.example.sweet_store.confectionery_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.R
import com.example.sweet_store.databinding.ActivityConfectioneryBinding
import com.example.sweet_store.databinding.ActivityConfectioneryProfileBinding
import com.example.sweet_store.page_controll.navigation.FooterBar
import com.example.sweet_store.page_controll.navigation.NavigationHeader

class ConfectioneryProfile : AppCompatActivity() {
    private lateinit var binding: ActivityConfectioneryProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfectioneryProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transition = supportFragmentManager.beginTransaction()

        val argumentsHeader = Bundle()
        argumentsHeader.putString("backButton","")
        argumentsHeader.putString("middleContent","Confeitaria")
        argumentsHeader.putString("pictureScr","")

        val argumentsCommentBox = Bundle()
        argumentsCommentBox.putString("picture","")
        argumentsCommentBox.putString("name","Le Docê")
        argumentsCommentBox.putString("stars","5")
        argumentsCommentBox.putString("address","Rua da Ponte, Guainases")

        val argumentsProductBox = Bundle()
        argumentsProductBox.putString("picture","")
        argumentsProductBox.putString("name","Brownie")
        argumentsProductBox.putString("description","Brownie de nutella")
        argumentsProductBox.putString("preco","R$12,00")

        val argumentsFooterBar = Bundle()



        transition.add(binding.containerHeader.id, NavigationHeader::class.java, argumentsHeader)
        transition.add(binding.containerCommentBox.id, ConfectioneryProfileBox::class.java, argumentsCommentBox)
        transition.add(binding.containerProductBox.id, ProductBox::class.java, argumentsProductBox)
        transition.add(binding.containerFooterBar.id, FooterBar::class.java, argumentsFooterBar)
        transition.commit()
    }


}