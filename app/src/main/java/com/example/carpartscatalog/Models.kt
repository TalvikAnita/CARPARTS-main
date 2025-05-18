package com.example.carpartscatalog


data class Brand(
    val id: Int,
    val name: String,
    val logoUrl: String
)


data class CarModel(
    val id: Int,
    val name: String
)


data class Part(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val description: String,
    val price: Double
)