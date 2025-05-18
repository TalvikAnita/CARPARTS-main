package com.example.carpartscatalog

import okhttp3.*
import org.json.JSONArray
import java.io.IOException

object NetworkUtils {
    fun getmodels (brand_id: Int): List<CarModel> {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://10.0.2.2:80/api/get_models.php?B_id=${brand_id}") // <-- адрес XAMPP-сервера
            .build()

        val response = client.newCall(request).execute()
        val modelList = mutableListOf<CarModel>()

        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            val jsonArray = JSONArray(responseBody)
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val name = item.getString("M_name")
                modelList.add(CarModel(name, R.drawable.placeholder))
            }
        }
        return modelList

    }
}
