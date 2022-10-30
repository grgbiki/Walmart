package com.bikram.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ShoppingCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)
        val username = intent.getStringExtra("username")
        findViewById<TextView>(R.id.welcome).text = "Welcome $username"

        val electronics = findViewById<LinearLayout>(R.id.category_electronics)
        val clothing = findViewById<LinearLayout>(R.id.category_clothing)
        val beauty = findViewById<LinearLayout>(R.id.category_beauty)
        val food = findViewById<LinearLayout>(R.id.category_food)

        electronics.setOnClickListener {
            showToast("You have chosen the Electronics category of shopping")
        }
        clothing.setOnClickListener {
            showToast("You have chosen the Clothing category of shopping")
        }
        beauty.setOnClickListener {
            showToast("You have chosen the Beauty category of shopping")
        }
        food.setOnClickListener {
            showToast("You have chosen the Food category of shopping")
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}