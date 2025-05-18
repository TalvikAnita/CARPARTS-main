package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CarModelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_models)

        // Получаем переданное имя автомобиля
        val carName = intent.getStringExtra("CAR_NAME") ?: "Unknown Car"

        // Устанавливаем название марки
        val textView: TextView = findViewById(R.id.car_name_text_view)
        textView.text = "$carName Models"

        // Список моделей для выбранного авто
        val modelList = when (carName) {
            "Skoda" -> listOf(
                CarModel("Octavia", R.drawable.skoda_octavia),
                CarModel("Superb", R.drawable.skoda_superb),
                CarModel("Kodiaq", R.drawable.skoda_kodiaq)
            )
            "BMW" -> listOf(
                CarModel("3 Series", R.drawable.bmw_3series),
                CarModel("X5", R.drawable.bmw_x5),
                CarModel("M5", R.drawable.bmw_m5)
            )
            "Audi" -> listOf(
                CarModel("A4", R.drawable.audi_a4),
                CarModel("Q7", R.drawable.audi_q7),
                CarModel("TT", R.drawable.audi_tt)
            )
            else -> emptyList()
        }

        // Подключаем адаптер для списка
        val listView: ListView = findViewById(R.id.model_list_view)
        val adapter = ModelAdapter(this, modelList)
        listView.adapter = adapter

        // При клике на модель — переход на список запчастей
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CarPartsActivity::class.java)
            intent.putExtra("MODEL_NAME", modelList[position].name)
            startActivity(intent)
        }
    }
}

// Класс модели авто
data class CarModel(val name: String, val imageResId: Int)