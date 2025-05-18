package com.example.carpartscatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CartFragment : Fragment() {

    private lateinit var cartListView: ListView
    private lateinit var emptyCartTextView: TextView
    private lateinit var adapter: CartAdapter
    private lateinit var cartItems: MutableList<Part> // Список для обновления

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        cartListView = view.findViewById(R.id.cart_list_view)
        emptyCartTextView = view.findViewById(R.id.empty_cart_text)


        cartItems = CartManager.getCartItems().toMutableList()

        if (cartItems.isEmpty()) {
            emptyCartTextView.visibility = View.VISIBLE
            cartListView.visibility = View.GONE
        } else {
            emptyCartTextView.visibility = View.GONE
            cartListView.visibility = View.VISIBLE
            adapter = CartAdapter(requireContext(), cartItems) { partId ->

                CartManager.removeFromCart(partId)
                cartItems.clear()
                cartItems.addAll(CartManager.getCartItems())
                if (cartItems.isEmpty()) {
                    emptyCartTextView.visibility = View.VISIBLE
                    cartListView.visibility = View.GONE
                } else {
                    emptyCartTextView.visibility = View.GONE
                    cartListView.visibility = View.VISIBLE
                }
                adapter.notifyDataSetChanged()
            }
            cartListView.adapter = adapter
        }

        return view
    }
}