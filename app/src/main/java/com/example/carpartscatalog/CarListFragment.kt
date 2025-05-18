package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

class CarListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_list, container, false)
        val listView: ListView = view.findViewById(R.id.listView)

        lifecycleScope.launch {
            try {
                val brandList = withContext(Dispatchers.IO) {
                    NetworkUtils.getBrands()
                }

                val adapter = CarListAdapter(requireContext(), brandList)
                listView.adapter = adapter

                listView.setOnItemClickListener { _, _, position, _ ->
                    val intent = Intent(requireContext(), CarModelsActivity::class.java)
                    intent.putExtra("CAR_NAME", brandList[position].name)
                    intent.putExtra("CAR_ID", brandList[position].id)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Ошибка загрузки брендов", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}