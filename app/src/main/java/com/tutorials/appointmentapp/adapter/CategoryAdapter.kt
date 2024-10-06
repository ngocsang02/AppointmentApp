package com.tutorials.appointmentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tutorials.appointmentapp.domain.CategoryModel
import com.tutorials.appointmentapp.R

class CategoryAdapter(val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.Viewholder>() {


    private lateinit var context: Context

    inner class Viewholder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(
            R.layout.viewholder_category,
            parent,
            false
        )
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        val imageView:ImageView = holder.itemView.findViewById<ImageView>(R.id.img)
        val titleTxt:TextView = holder.itemView.findViewById(R.id.titleTxt)

        titleTxt.text = item.Name

        Glide.with(context)
            .load(item.Picture)
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}