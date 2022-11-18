package com.example.sweet_store.ui.confectionery

import android.graphics.BitmapFactory
import android.net.Uri.decode
import android.util.Base64.DEFAULT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import java.lang.Byte.decode
import java.util.*
import android.graphics.Bitmap
import android.util.Base64
import java.nio.charset.StandardCharsets
import kotlin.collections.ArrayList

class HomeAdapter (private val confectionery: ArrayList<ConfectioneryVO>) :
    RecyclerView.Adapter<HomeAdapter.ViewHomeHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ViewHomeHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_confectionery, parent, false)
        return HomeAdapter.ViewHomeHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: HomeAdapter.ViewHomeHolder,
        position: Int
    ) {



        var street: String
        val currentItem = confectionery[position]
        street = currentItem.address.street?: ""
        holder.name.text = currentItem.fantasyName
        holder.address.text = street

    }

    override fun getItemCount() = confectionery.size
    fun convertStringToBitmap(base64Str: String?): Bitmap? {
        val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
    class ViewHomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idConfectionery: Int = 0
        val name: TextView = itemView.findViewById(R.id.name_confectionery)
        val address: TextView = itemView.findViewById(R.id.adress_confectionery)
        val image: ImageView = itemView.findViewById(R.id.img_confectionery)

    }

}
