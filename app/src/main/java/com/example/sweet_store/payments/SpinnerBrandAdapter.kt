package com.example.sweet_store.payments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.sweet_store.R

class SpinnerBrandAdapter(
    private var context: Context,
    private var images: IntArray,
) :
    BaseAdapter() {
    private var inflter: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val view = inflter.inflate(R.layout.spinner_brand_layout, null)
        val icon = view.findViewById<View>(R.id.spinner_brand_image) as ImageView?
        icon!!.setImageResource(images[i])
        return view
    }
}