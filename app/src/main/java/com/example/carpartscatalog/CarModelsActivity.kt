package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class CarModelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_models)

        val carName = intent.getStringExtra("CAR_NAME") ?: "Unknown Car"
        val carId = intent.getIntExtra("CAR_ID", 1)

        val textView: TextView = findViewById(R.id.car_name_text_view)
        textView.text = "$carName Models"

        val listView: ListView = findViewById(R.id.model_list_view)

        lifecycleScope.launch {
            try {
                val modelList = withContext(Dispatchers.IO) {
                    NetworkUtils.getModels(carId)
                }

                val adapter = ModelAdapter(this@CarModelsActivity, modelList)
                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, position, _ ->
                    val intent = Intent(this@CarModelsActivity, CarPartsActivity::class.java)
                    intent.putExtra("MODEL_NAME", modelList[position].name)
                    intent.putExtra("MODEL_ID", modelList[position].id)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@CarModelsActivity, "Error loading models", Toast.LENGTH_SHORT).show()
            }
        }
    }
}