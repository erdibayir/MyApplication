package com.elifersumer.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elifersumer.myapplication.Database.DoneOrder
import com.elifersumer.myapplication.R
import com.elifersumer.myapplication.fragments.hisseler

class RecyclerViewAdapterGeEmirlerim(var hisse_list: MutableList<DoneOrder>) :
    RecyclerView.Adapter<RecyclerViewAdapterGeEmirlerim.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val isim = view.findViewById<TextView>(R.id.GeHisse)
        val adet = view.findViewById<TextView>(R.id.GeAdet)
        val fiyat = view.findViewById<TextView>(R.id.GeFiyat)
        val alisOrSatis = view.findViewById<TextView>(R.id.GeAlisOrSatis)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout_gerceklesen, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position % 2  == 0){
            holder.isim.setBackgroundColor(Color.parseColor("#C7D3E1"))
            holder.adet.setBackgroundColor(Color.parseColor("#C7D3E1"))
            holder.fiyat.setBackgroundColor(Color.parseColor("#C7D3E1"))
            holder.alisOrSatis.setBackgroundColor(Color.parseColor("#C7D3E1"))

        }
        else{
            holder.isim.setBackgroundColor(Color.parseColor("#f5f5f5"))
            holder.adet.setBackgroundColor(Color.parseColor("#f5f5f5"))
            holder.fiyat.setBackgroundColor(Color.parseColor("#f5f5f5"))
            holder.alisOrSatis.setBackgroundColor(Color.parseColor("#f5f5f5"))
        }
        holder.isim.text = hisse_list[position].Hisse
        holder.adet.text = hisse_list[position].Adet
        holder.fiyat.text = hisse_list[position].Fiyat
        holder.alisOrSatis.text = hisse_list[position].IslemTipi

        if(hisse_list[position].IslemTipi == "Alış"){
            holder.alisOrSatis.setTextColor(Color.parseColor("#2D8326"))
        }
        else
            holder.alisOrSatis.setTextColor(Color.parseColor("#cb4937"))
    }



    override fun getItemCount(): Int {
        return hisse_list.size
    }

}

