package com.scrumptious.algorithmvisualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the login button by its ID
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set an onClickListener for the login button
        loginButton.setOnClickListener {
            // Show a toast message when the login button is clicked
            Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
