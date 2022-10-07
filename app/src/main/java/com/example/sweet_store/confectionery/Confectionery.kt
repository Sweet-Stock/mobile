package com.example.sweet_store.confectionery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.databinding.ActivityConfectioneryBinding
import com.example.sweet_store.page_controll.navigation.FooterBar
import com.example.sweet_store.page_controll.navigation.NavigationHeader

class Confectionery : AppCompatActivity() {
    private lateinit var binding: ActivityConfectioneryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfectioneryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transition = supportFragmentManager.beginTransaction()

        val argumentsHeader = Bundle()
        argumentsHeader.putString("backButton","")
        argumentsHeader.putString("middleContent","Confeitarias")
        argumentsHeader.putString("pictureScr","")

        val argumentsCommentBox = Bundle()
        argumentsCommentBox.putString("picture","")
        argumentsCommentBox.putString("name","Alyce Lambo")
        argumentsCommentBox.putString("star","4,7")
        argumentsCommentBox.putString("endereco","Rua da Ponte, Guainases")

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
        transition.add(fragmentConfectioneryBox.id, ConfectioneryBox::class.java, argumentsCommentBox)
        transition.add(fragmentFooterBar.id, FooterBar::class.java, argumentsFooterBar)
        transition.commit()
    }

}
