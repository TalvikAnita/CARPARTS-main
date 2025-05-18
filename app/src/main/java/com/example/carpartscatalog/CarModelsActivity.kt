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
import android.util.Log


class CarModelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_models)

        // Получаем переданное имя автомобиля
        val carName = intent.getStringExtra("CAR_NAME") ?: "Unknown Car"
        val carid = intent.getIntExtra("CAR_ID", 1)+1
        // Устанавливаем название марки
        val textView: TextView = findViewById(R.id.car_name_text_view)
        textView.text = "$carName Models"

        val listView: ListView = findViewById(R.id.model_list_view)

        // 🚀 Запуск корутины
        lifecycleScope.launch {
            try {
                // ⚙️ Получаем список моделей в фоне
                val modelList = withContext(Dispatchers.IO) {
                    Log.d("", "жопа ${carid}")

                    NetworkUtils.getmodels(carid)
                }

                // ✅ Обновляем UI в главном потоке
                val adapter = ModelAdapter(this@CarModelsActivity, modelList)
                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, position, _ ->
                    val intent = Intent(this@CarModelsActivity, CarPartsActivity::class.java)
                    intent.putExtra("MODEL_NAME", modelList[position].name)
                    startActivity(intent)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                // Здесь можно показать сообщение об ошибке
            }
        }
    }
}
data class CarModel(val name: String, val imageResId: Int)