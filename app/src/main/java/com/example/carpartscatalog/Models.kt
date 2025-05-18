package com.example.carpartscatalog

// Модель для брендов
data class Brand(
    val id: Int,
    val name: String,
    val logoUrl: String // URL логотипа
)

// Модель для моделей автомобилей
data class CarModel(
    val id: Int,
    val name: String
)

// Модель для запчастей
data class Part(
    val id: Int,
    val name: String,
    val logoUrl: String, // URL логотипа
    val description: String, // Описание запчасти
    val price: Double // Цена запчасти
)