package com.bikram.walmart

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstNameEditText = findViewById<EditText>(R.id.first_name_edit_text)
        val lastNameEditText = findViewById<EditText>(R.id.last_name_edit_text)
        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val createAccountButton = findViewById<Button>(R.id.create_account_button)

        createAccountButton.setOnClickListener {
            if (firstNameEditText.text.isNotBlank() && lastNameEditText.text.isNotBlank() && emailEditText.text.isNotBlank() && passwordEditText.text.isNotBlank()) {
                val newUser = User(
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                )
                val data = Intent()
                data.putExtra("newuser", newUser)
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }
    }
}