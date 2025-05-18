package com.example.carpartscatalog

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PartDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_detail)


        val partName = intent.getStringExtra("PART_NAME") ?: "Unknown Part"
        val partLogo = intent.getStringExtra("PART_LOGO") ?: ""
        val partDescription = intent.getStringExtra("PART_DESCRIPTION") ?: "No description"
        val partPrice = intent.getDoubleExtra("PART_PRICE", 0.0)
        val partId = intent.getIntExtra("PART_ID", 0)


        val part = Part(partId, partName, partLogo, partDescription, partPrice)


        val partImageView: ImageView = findViewById(R.id.part_image)
        val partDescriptionTextView: TextView = findViewById(R.id.part_description)
        val partPriceTextView: TextView = findViewById(R.id.part_price)
        val addToCartButton: Button = findViewById(R.id.add_to_cart_button)


        if (partLogo.isNotEmpty()) {
            Glide.with(this)
                .load(partLogo)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(partImageView)
        } else {
            partImageView.setImageResource(R.drawable.placeholder)
        }

        partDescriptionTextView.text = partDescription
        partPriceTextView.text = "Price: $%.2f".format(partPrice)
        title = partName


        addToCartButton.setOnClickListener {
            CartManager.addToCart(part)
            Toast.makeText(this, "Added to cart: $partName", Toast.LENGTH_SHORT).show()
        }
    }
}