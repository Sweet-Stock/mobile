package com.example.sweet_store.page_controll.rating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sweet_store.R

class CommentBox : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //      val backButton = arguments?.getString("backButton").toString()
        val picture = arguments?.getString("picture").toString()
        val name = arguments?.getString("name").toString()
        val rating = arguments?.getString("rating").toString()
        val date = arguments?.getString("date").toString()
        val text = arguments?.getString("text").toString()
//        val pictureScr = arguments?.getParcelable<Bitmap>("pictureScr")

        view.findViewById<TextView>(R.id.boxText).text = text
        view.findViewById<TextView>(R.id.profileName).text = name
        view.findViewById<TextView>(R.id.commentRating).text = rating
        view.findViewById<TextView>(R.id.commentDate).text = date
        view.findViewById<TextView>(R.id.boxText).text = text

    }
}