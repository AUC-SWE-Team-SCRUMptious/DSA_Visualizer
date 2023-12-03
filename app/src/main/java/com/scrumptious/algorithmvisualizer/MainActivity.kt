package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scrumptious.algorithmvisualizer.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //Activity link to register page
    private lateinit var binding: ActivityMainBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        var user = firebaseAuth.getCurrentUser()

        binding.accountManager.setOnClickListener {
            if (user!=null)
            {
                val intent = Intent(this, AccountManagerActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            else
            {
                Toast.makeText(this, "You are not logged in to use this feature", Toast.LENGTH_SHORT).show() //output message
            }
        }
    }


}