package com.example.carpartscatalog

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException

object NetworkUtils {

    private val client = OkHttpClient()


    fun getBrands(): List<Brand> {
        val request = Request.Builder()
            .url("http://10.0.2.2:80/api/get_brands.php")
            .build()

        val response = client.newCall(request).execute()
        val brandList = mutableListOf<Brand>()

        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            val jsonArray = JSONArray(responseBody)
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val id = item.getInt("B_id")
                val name = item.getString("B_name")
                val logoUrl = item.getString("B_logo")
                brandList.add(Brand(id, name, logoUrl))
            }
        }
        return brandList
    }


    fun getModels(brandId: Int): List<CarModel> {
        val request = Request.Builder()
            .url("http://10.0.2.2:80/api/get_models.php?B_id=$brandId")
            .build()

        val response = client.newCall(request).execute()
        val modelList = mutableListOf<CarModel>()

        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            val jsonArray = JSONArray(responseBody)
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val id = item.getInt("M_id")
                val name = item.getString("M_name")
                modelList.add(CarModel(id, name))
            }
        }
        return modelList
    }


    fun getParts(modelId: Int): List<Part> {
        val request = Request.Builder()
            .url("http://10.0.2.2:80/api/get_parts.php?M_id=$modelId")
            .build()

        val response = client.newCall(request).execute()
        val partsList = mutableListOf<Part>()

        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            val jsonArray = JSONArray(responseBody)
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val id = item.getInt("P_id")
                val name = item.getString("P_name")
                val logoUrl = item.getString("P_logo")
                val description = item.getString("P_description")
                val price = item.getDouble("P_price")
                partsList.add(Part(id, name, logoUrl, description, price))
            }
        }
        return partsList
    }
}