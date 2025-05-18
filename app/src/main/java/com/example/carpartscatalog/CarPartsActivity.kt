package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class CarPartsActivity : AppCompatActivity() {
    private val TAG = "CarPartsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_parts)

        val modelName = intent.getStringExtra("MODEL_NAME") ?: "Unknown Model"
        val modelId = intent.getIntExtra("MODEL_ID", 0)
        Log.d(TAG, "Loading parts for model: $modelName, M_id: $modelId")

        val textView: TextView = findViewById(R.id.model_name_text_view)
        textView.text = "$modelName Parts"

        val listView: ListView = findViewById(R.id.parts_list_view)

        lifecycleScope.launch {
            try {
                val partsList = withContext(Dispatchers.IO) {
                    NetworkUtils.getParts(modelId)
                }
                Log.d(TAG, "Parts loaded: ${partsList.size} items")

                if (partsList.isEmpty()) {
                    Toast.makeText(this@CarPartsActivity, "Нет запчастей для этой модели", Toast.LENGTH_SHORT).show()
                }

                val adapter = PartsAdapter(this@CarPartsActivity, partsList)
                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, position, _ ->
                    val part = partsList[position]
                    val intent = Intent(this@CarPartsActivity, PartDetailActivity::class.java)
                    intent.putExtra("PART_ID", part.id)
                    intent.putExtra("PART_NAME", part.name)
                    intent.putExtra("PART_LOGO", part.logoUrl)
                    intent.putExtra("PART_DESCRIPTION", part.description)
                    intent.putExtra("PART_PRICE", part.price)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading parts: ${e.message}", e)
                Toast.makeText(this@CarPartsActivity, "Error loading parts: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}