package com.example.carpartscatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val cartListView: ListView = view.findViewById(R.id.cart_list_view)
        val emptyCartTextView: TextView = view.findViewById(R.id.empty_cart_text)

        // Получаем список запчастей в корзине
        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            emptyCartTextView.visibility = View.VISIBLE
            cartListView.visibility = View.GONE
        } else {
            emptyCartTextView.visibility = View.GONE
            cartListView.visibility = View.VISIBLE
            val adapter = CartAdapter(requireContext(), cartItems)
            cartListView.adapter = adapter
        }

        return view
    }
}