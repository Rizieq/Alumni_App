package com.project.alumniapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.alumniapp.R
import com.project.alumniapp.model.Data
import com.project.alumniapp.model.DataItem
import kotlinx.android.synthetic.main.item_row.view.*

class AdapterListAlumni(val context: Context, val listAlumni: List<DataItem>): RecyclerView.Adapter<AdapterListAlumni.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterListAlumni.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return AdapterListAlumni.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAlumni.size
    }

    override fun onBindViewHolder(holder: AdapterListAlumni.MyViewHolder, position: Int) {
        holder.bindItem(listAlumni[position])
    }

    class MyViewHolder (view: View):RecyclerView.ViewHolder(view) {
        val imageCvAlumni = view.imgCvAlumni
        val imageProfileAlumni = view.imgProfileAlumni
        val tv_nama_alumni = view.tvNamaAlumni
        val tv_graduation = view.tvGraduation




        fun bindItem(dataItem: DataItem) {
            tv_nama_alumni.text = dataItem.name
            tv_graduation.text = dataItem.tahunAlumni
            Glide.with(itemView)
                .load("https://ujikom.phoenixcendekia.com/images/" + dataItem.image)
                .into(imageProfileAlumni)
            Glide.with(itemView)
                .load("https://ujikom.phoenixcendekia.com/cv/"+ dataItem.cv)
                .into(imageCvAlumni)
        }
    }
}