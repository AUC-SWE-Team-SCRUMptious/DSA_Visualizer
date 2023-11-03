package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scrumptious.algorithmvisualizer.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the login button by its ID
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set an onClickListener for the login button
        loginButton.setOnClickListener {
            // Show a toast message when the login button is clicked
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()

        }
    }
}
