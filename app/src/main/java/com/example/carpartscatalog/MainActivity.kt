package com.example.carpartscatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Показываем CarListFragment при старте
        replaceFragment(CarListFragment())

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_car_list -> replaceFragment(CarListFragment()) // Нажатие на "Car List"
                R.id.nav_contact_info -> replaceFragment(ContactInfoFragment()) // Нажатие на "Contact Info"
                R.id.nav_cart -> replaceFragment(CartFragment()) // Нажатие на "Cart"
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}