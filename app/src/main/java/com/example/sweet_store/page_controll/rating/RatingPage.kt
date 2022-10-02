package com.example.sweet_store.page_controll.rating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.example.sweet_store.page_controll.navigation.FooterBar
import com.example.sweet_store.databinding.ActivityRatingPageBinding
import com.example.sweet_store.page_controll.navigation.NavigationHeader

class RatingPage : AppCompatActivity() {
    private lateinit var binding: ActivityRatingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transition = supportFragmentManager.beginTransaction()

        val argumentsHeader = Bundle()
        argumentsHeader.putString("backButton","")
        argumentsHeader.putString("middleContent","Avaliações")
        argumentsHeader.putString("pictureScr","")

        val argumentsCommentBox = Bundle()
        argumentsCommentBox.putString("picture","")
        argumentsCommentBox.putString("name","Alyce Lambo")
        argumentsCommentBox.putString("rating","4,7")
        argumentsCommentBox.putString("date","25/06/2020")
        argumentsCommentBox.putString("text","Really convenient and the points system helps benefit loyalty. Some mild glitches here and there, but nothing too egregious. Obviously needs to roll out to more remote.")

        val argumentsFooterBar = Bundle()

        val fragmentHeader = FragmentContainerView(applicationContext)
        val fragmentCommentBox = FragmentContainerView(applicationContext)
        val fragmentFooterBar = FragmentContainerView(applicationContext)

        fragmentHeader.id = View.generateViewId()
        fragmentCommentBox.id = View.generateViewId()
        fragmentFooterBar.id = View.generateViewId()

        binding.containerHeader.addView(fragmentHeader)
        binding.containerCommentBox.addView(fragmentCommentBox)
        binding.containerFooterBar.addView(fragmentFooterBar)

        transition.add(fragmentHeader.id, NavigationHeader::class.java, argumentsHeader)
        transition.add(fragmentCommentBox.id, CommentBox::class.java, argumentsCommentBox)
        transition.add(fragmentFooterBar.id, FooterBar::class.java, argumentsFooterBar)
        transition.commit()
    }

}