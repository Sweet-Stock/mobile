package com.example.sweet_store.ui.confectionery

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.ConfectioneryActivity
import com.example.sweet_store.R


class ConfectioneryAdapter(private val confectionery: ArrayList<ConfectioneryVO>) :
    RecyclerView.Adapter<ConfectioneryAdapter.ViewConfectioneryHolder>() {


    var con: Context? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfectioneryAdapter.ViewConfectioneryHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_confectionery, parent, false)
        return ConfectioneryAdapter.ViewConfectioneryHolder(itemView)
    }

    private fun formatBase64(code: String): String {
        var formattedString = code

        formattedString = formattedString.replace("data:image/png;base64,", "")
        formattedString = formattedString.replace("data:image/svg;base64,", "")
        formattedString = formattedString.replace("data:image/svg+xml;base64,", "")
        formattedString = formattedString.replace("data:image/jpeg;base64,", "")
        formattedString = formattedString.replace("data:image/jpg;base64,", "")
        return formattedString
    }

    override fun onBindViewHolder(
        holder: ConfectioneryAdapter.ViewConfectioneryHolder,
        position: Int
    ) {

        var street: String
        val currentItem = confectionery[position]
        if  (!currentItem.picture.isNullOrEmpty()){
            val bruteBase64 = currentItem.picture

            val base64 = formatBase64(bruteBase64)
            holder.image.setImageBitmap(convertStringToBitmap(base64))
        }

        street = currentItem.address.street ?: ""
        holder.name.text = currentItem.fantasyName
        holder.address.text = street

        holder.uuid = currentItem.uuid
        holder.image.setOnClickListener {
        }
    }

    override fun getItemCount() = confectionery.size
    fun convertStringToBitmap(base64Str: String?): Bitmap? {
        val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    class ViewConfectioneryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uuid: String = ""
        val name: TextView = itemView.findViewById(R.id.name_confectionery)
        val address: TextView = itemView.findViewById(R.id.adress_confectionery)
        val image: ImageView = itemView.findViewById(R.id.img_confectionery)

        init {
            this.itemView.setOnClickListener {
                val detalheCliente = Intent(it.context, ConfectioneryActivity::class.java)
                detalheCliente.putExtra("idConfectionery", uuid)
                it.context.startActivity(detalheCliente)
            }
        }
    }

}
