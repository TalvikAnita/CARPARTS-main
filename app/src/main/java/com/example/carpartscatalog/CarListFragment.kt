package com.example.carpartscatalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.carpartscatalog.CarItem

class CarListFragment : Fragment() {
    private val carList = listOf(
        CarItem("Skoda", R.drawable.skoda),
        CarItem("BMW", R.drawable.bmw),
        CarItem("Audi", R.drawable.audi)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_car_list, container, false)

        // Настроим ListView
        val listView: ListView = view.findViewById(R.id.listView)
        val adapter = CarListAdapter(requireContext(), carList)
        listView.adapter = adapter

        // Обработчик клика на элемент списка
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), CarModelsActivity::class.java)
            intent.putExtra("CAR_NAME", carList[position].name)
            intent.putExtra("CAR_ID", position)
            startActivity(intent)
        }

        return view
    }
}