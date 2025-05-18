package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter

class ModelAdapter(private val context: Context, private val models: List<CarModel>) : BaseAdapter() {

    override fun getCount(): Int = models.size

    override fun getItem(position: Int): Any = models[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_model, parent, false)
        val model = models[position]

        val imageView: ImageView = view.findViewById(R.id.model_image)
        val textView: TextView = view.findViewById(R.id.model_name)

        imageView.setImageResource(model.imageResId)
        textView.text = model.name

        return view
    }

}