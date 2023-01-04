package com.example.catinder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter (private val names: List<String>, private val result: List<String>): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){//Инициализация объектов лайаута айтемов ресайклера
    val nameText: TextView = itemView.findViewById(R.id.nameText)
        val resultText: TextView = itemView.findViewById(R.id.resultText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { //Подвязка лайаута к адаптеру ресайклера
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false) //Определение лайаута
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //Запись в айтем данных в заготовленные textview
        holder.nameText.text = names[position]
        holder.resultText.text = result[position]
    }

    override fun getItemCount(): Int {
        return result.size
    }
}