package com.bikram.walmart

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.email_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val signInButton = findViewById<Button>(R.id.sign_in_button)
        val createAccountButton = findViewById<Button>(R.id.create_account_button)
        val forgetPassword = findViewById<TextView>(R.id.forget_password)

        generateUsers()

        signInButton.setOnClickListener {
            if (emailEditText.text.isNotBlank() && passwordEditText.text.isNotBlank()) {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                val index = users.indexOfFirst { it.username == email && it.password == password }
                if (index >= 0) {
                    val user = users[index]
                    val intent = Intent(this, ShoppingCategory::class.java)
                    intent.putExtra("username", user.username)
                    startActivity(intent)
                }else{
                    showToast("Incorrect username or password")
                }
            }
        }

        forgetPassword.setOnClickListener {
            if (emailEditText.text.isNotBlank()) {
                val recipientMail = emailEditText.text.toString()
                val index = users.indexOfFirst { it.username == recipientMail }
                if (index >= 0) {
                    val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
                    emailSelectorIntent.data = Uri.parse("mailto:")

                    val emailIntent = Intent(Intent.ACTION_SEND)
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientMail))
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is: ${users[index].password}")
                    emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    emailIntent.selector = emailSelectorIntent

                    startActivity(Intent.createChooser(emailIntent, null))
                } else {
                    showToast("Username does not exist")
                }
            } else {
                showToast("Please input email first")
            }
        }

        var startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // There are no request codes
                    val newUser = result.data?.getSerializableExtra("newuser") as User
                    if (newUser != null) {
                        users.add(newUser)
                    }
                }
            }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startForResult.launch(intent)
        }
    }

    fun generateUsers() {
        val user1 = User("Bikram", "Gurung", "gurung@gmail.com", "bikram")
        val user2 = User("John", "Doe", "john@gmail.com", "john")
        val user3 = User("Shawn", "Michael", "shawn@gmail.com", "shawn")
        val user4 = User("Ron", "Willis", "ron@gmail.com", "ron")
        val user5 = User("Millie", "Kate", "kate@gmail.com", "millie")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}