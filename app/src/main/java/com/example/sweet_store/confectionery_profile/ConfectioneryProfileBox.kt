package com.example.sweet_store.confectionery_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sweet_store.R


class ConfectioneryProfileBox: Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confectionery_profile_box, container, false)
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //      val backButton = arguments?.getString("backButton").toString()
    val picture = arguments?.getString("picture").toString()
    val name = arguments?.getString("name").toString()
    val stars = arguments?.getString("star").toString()
    val address = arguments?.getString("endereco").toString()
//        val pictureScr = arguments?.getParcelable<Bitmap>("pictureScr")


       view.findViewById<TextView>(R.id.confectionery_name).text = name
       view.findViewById<TextView>(R.id.confectionery_name2).text = name
       view.findViewById<TextView>(R.id.confectionery_address).text = address
       view.findViewById<TextView>(R.id.confectionaryRating).text = stars
//    view.findViewById<TextView>(R.id.confectionaryRating).text = stars
//    view.findViewById<TextView>(R.id.locationName).text = endereco


}
}