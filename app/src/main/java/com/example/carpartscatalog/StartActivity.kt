package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start) // Указываем, какую разметку использовать

        val startButton: Button = findViewById(R.id.start_button) // Ищем кнопку по её ID
        startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Переход на главный экран
            startActivity(intent) // Запуск MainActivity
        }
    }
}