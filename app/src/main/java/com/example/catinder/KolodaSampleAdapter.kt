package com.example.catinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class KolodaSampleAdapter(val context: Context, val data: List<String>?) : BaseAdapter() {
    private val dataList = mutableListOf<String>()

    init {
        if (data != null) {
            dataList.addAll(data)
        }
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): String {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val holder: DataViewHolder
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_koloda, parent, false)
            holder = DataViewHolder(view)
            view?.tag = holder

        } else {
            holder = view.tag as DataViewHolder
        }

        holder.bindData(getItem(position))
    //    println("check position ${position}")

        return view
    }

    /**
     * Static view items holder
     */
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var picture = view.findViewById<ImageView>(R.id.catImg)

        internal fun bindData( data: String) {
            Picasso.get().load(data).into(picture);
       //     println("data check ${data}")
        }

    }
}