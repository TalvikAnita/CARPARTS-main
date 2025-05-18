package com.example.carpartscatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button // Импортируем Button
import android.widget.TextView

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<Part>,
    private val onRemoveItem: (Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = cartItems.size

    override fun getItem(position: Int): Any = cartItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_cart, parent, false)
        val part = cartItems[position]

        val nameTextView: TextView = view.findViewById(R.id.cart_item_name)
        val priceTextView: TextView = view.findViewById(R.id.cart_item_price)
        val removeButton: Button = view.findViewById(R.id.remove_button)

        nameTextView.text = part.name
        priceTextView.text = "Price: $%.2f".format(part.price)

        removeButton.setOnClickListener {
            onRemoveItem(part.id)
        }

        return view
    }
}