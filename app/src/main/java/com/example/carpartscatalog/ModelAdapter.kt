package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ModelAdapter(private val context: Context, private val models: List<CarModel>) : BaseAdapter() {

    override fun getCount(): Int = models.size

    override fun getItem(position: Int): Any = models[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_model, parent, false)
        val model = models[position]

        val imageView: ImageView = view.findViewById(R.id.model_image)
        val textView: TextView = view.findViewById(R.id.model_name)

        textView.text = model.name
        if (model.logoUrl.isNotEmpty()) {
            Glide.with(context)
                .load(model.logoUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.placeholder)
        }

        return view
    }
}