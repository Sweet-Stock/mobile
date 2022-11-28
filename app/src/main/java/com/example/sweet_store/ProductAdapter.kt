package com.example.sweet_store

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
import com.example.sweet_store.model.response.ProductVO
import com.example.sweet_store.ui.confectionery.ConfectioneryAdapter
import com.example.sweet_store.ui.confectionery.ConfectioneryVO

class ProductAdapter(private val product: ArrayList<ProductVO>) :
    RecyclerView.Adapter<ProductAdapter.ViewProductHolder>() {


    var con: Context? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewProductHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_product, parent, false)
        return ProductAdapter.ViewProductHolder(itemView)
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
        holder: ProductAdapter.ViewProductHolder,
        position: Int
    ) {

        var street: String
        val currentItem = product[position]
        holder.name.text = currentItem.name
        // holder.description.text = currentI

        val bruteBase64 = currentItem.picture
        val base64 = formatBase64(bruteBase64)
        holder.image.setImageBitmap(convertStringToBitmap(base64))
        holder.uuid = currentItem.uuid
        holder.value.text = currentItem.saleValue

    }

    override fun getItemCount() = product.size
    fun convertStringToBitmap(base64Str: String?): Bitmap? {
        val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    class ViewProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uuid: String = ""
        val name: TextView = itemView.findViewById(R.id.name_product)
        val description: TextView = itemView.findViewById(R.id.description_product)
        val image: ImageView = itemView.findViewById(R.id.image_product)
        val value: TextView = itemView.findViewById(R.id.value_product)

        init {
            this.itemView.setOnClickListener {
                val detail = Intent(it.context, ConfectioneryActivity::class.java)
                detail.putExtra("idConfectionery", uuid)
                it.context.startActivity(detail)
            }
        }
    }

}