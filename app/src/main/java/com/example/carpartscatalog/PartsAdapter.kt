package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PartsAdapter(private val context: Context, private val parts: List<Part>) : BaseAdapter() {

    override fun getCount(): Int = parts.size

    override fun getItem(position: Int): Any = parts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_part, parent, false)
        val part = parts[position]

        val textView: TextView = view.findViewById(R.id.part_name)
        textView.text = part.name

        return view
    }
}