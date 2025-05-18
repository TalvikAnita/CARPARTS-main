package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CarListAdapter(private val context: Context, private val carList: List<CarItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return carList.size
    }

    override fun getItem(position: Int): Any {
        return carList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_car, parent, false)
        }

        val carItem = carList[position]
        val carNameTextView: TextView = view!!.findViewById(R.id.car_name)
        val carImageView: ImageView = view.findViewById(R.id.car_image)

        carNameTextView.text = carItem.name
        carImageView.setImageResource(carItem.imageResId)

        return view
    }
}