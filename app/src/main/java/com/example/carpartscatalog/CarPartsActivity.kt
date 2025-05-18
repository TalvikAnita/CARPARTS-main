package com.example.carpartscatalog

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CarPartsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_parts)

        // Получаем переданное название модели
        val modelName = intent.getStringExtra("MODEL_NAME") ?: "Unknown Model"

        // Устанавливаем название модели в TextView
        val textView: TextView = findViewById(R.id.model_name_text_view)
        textView.text = "$modelName Parts"

        // Определяем список запчастей в зависимости от модели
        val partsList = when (modelName) {
            "Octavia" -> listOf("Тормозные колодки", "Фары")
            "Superb" -> listOf("Свечи зажигания", "Аккумулятор", "Диски")
            "Kodiaq" -> listOf("Амортизаторы", "Глушитель", "Фильтр салона")

            "3 Series" -> listOf("Ремень ГРМ", "Масляный насос", "Колёса")
            "X5" -> listOf("Рулевые тяги", "Воздушный фильтр", "Турбина")
            "M5" -> listOf("Тормозные диски", "ШРУС", "Сцепление")

            "A4" -> listOf("Фильтр топлива", "Датчик кислорода", "Радиатор")
            "Q7" -> listOf("Форсунки", "Поршни", "Генератор")
            "TT" -> listOf("Шаровые опоры", "Маховик", "Гидроусилитель руля")

            else -> listOf("Нет данных")
        }

        // Подключаем адаптер и показываем запчасти
        val listView: ListView = findViewById(R.id.parts_list_view)
        val adapter = PartsAdapter(this, partsList)
        listView.adapter = adapter
    }
}