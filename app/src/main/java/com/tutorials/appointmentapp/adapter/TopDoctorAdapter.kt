package com.tutorials.appointmentapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.tutorials.appointmentapp.domain.DoctorsModel
import com.tutorials.appointmentapp.R
import com.tutorials.appointmentapp.activity.DetailActivity

class TopDoctorAdapter(val items:MutableList<DoctorsModel>):RecyclerView.Adapter<TopDoctorAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(
            R.layout.viewholder_top_doctor,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopDoctorAdapter.ViewHolder, position: Int) {
        val imgDoctor = holder.itemView.findViewById<ImageView>(R.id.imgDoctor)
        val nameDoctor = holder.itemView.findViewById<TextView>(R.id.nameTxt)
        val specialDoctor = holder.itemView.findViewById<TextView>(R.id.specialTxt)
        val ratingDoctor = holder.itemView.findViewById<TextView>(R.id.ratingTxt)
        val expDoctor = holder.itemView.findViewById<TextView>(R.id.yearTxt)


        nameDoctor.text = items[position].Name
        specialDoctor.text = items[position].Special
        ratingDoctor.text = items[position].Rating.toString()
        expDoctor.text = items[position].Expriense.toString() + " Year"

        Glide.with(context)
            .load(items[position].Picture)
            .apply {
                RequestOptions().transform(CenterCrop())
            }
            .into(imgDoctor)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("object", items[position])
                context.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}