package com.example.sweet_store.ui.confectionery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet_store.R
import com.example.sweet_store.model.response.ConfectioneryResponse

class ConfectioneryAdapter(private val confectionery: ArrayList<ConfectioneryVO>):
    RecyclerView.Adapter<ConfectioneryAdapter.ViewConfectioneryHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfectioneryAdapter.ViewConfectioneryHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_confectionery, parent, false)
        return ConfectioneryAdapter.ViewConfectioneryHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConfectioneryAdapter.ViewConfectioneryHolder, position: Int) {
        val currentItem = confectionery[position]
      //  holder.name = currentItem.fantasyName

    }

    override fun getItemCount() = confectionery.size

    class ViewConfectioneryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idConfectionery: Int = 0
        val name: TextView = itemView.findViewById(R.id.name_confectionery)
        val adress: TextView = itemView.findViewById(R.id.adress_confectionery)
        val btn: Button = itemView.findViewById(R.id.btn_confectionery)

        init {
            this.btn.setOnClickListener {
//                val detalheCliente = Intent(it.context, AlterarStatusActivity::class.java)
//                detalheCliente.putExtra("idServico", idServico)
             //   it.context.startActivity(detalheCliente)
              //  Toast.makeText( , "cliquei", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
