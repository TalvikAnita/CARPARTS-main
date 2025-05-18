package com.example.carpartscatalog

object CartManager {
    private val cartItems = mutableListOf<Part>()

    fun addToCart(part: Part) {
        cartItems.add(part)
    }

    fun getCartItems(): List<Part> {
        return cartItems.toList()
    }

    fun clearCart() {
        cartItems.clear()
    }
}