package com.example.carpartscatalog

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class CarPartsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_parts)

        val modelName = intent.getStringExtra("MODEL_NAME") ?: "Unknown Model"
        val modelId = intent.getIntExtra("MODEL_ID", 0)

        val textView: TextView = findViewById(R.id.model_name_text_view)
        textView.text = "$modelName Parts"

        val listView: ListView = findViewById(R.id.parts_list_view)

        lifecycleScope.launch {
            try {
                val partsList = withContext(Dispatchers.IO) {
                    NetworkUtils.getParts(modelId)
                }

                val adapter = PartsAdapter(this@CarPartsActivity, partsList)
                listView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@CarPartsActivity, "Ошибка загрузки запчастей", Toast.LENGTH_SHORT).show()
            }
        }
    }
}