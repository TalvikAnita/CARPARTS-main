package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CarListAdapter(private val context: Context, private val brandList: List<Brand>) : BaseAdapter() {

    override fun getCount(): Int = brandList.size

    override fun getItem(position: Int): Any = brandList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_car, parent, false)
        val brand = brandList[position]

        val carNameTextView: TextView = view.findViewById(R.id.car_name)
        val carImageView: ImageView = view.findViewById(R.id.car_image)

        carNameTextView.text = brand.name
        if (brand.logoUrl.isNotEmpty()) {
            Glide.with(context)
                .load(brand.logoUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(carImageView)
        } else {
            carImageView.setImageResource(R.drawable.placeholder)
        }

        return view
    }
}