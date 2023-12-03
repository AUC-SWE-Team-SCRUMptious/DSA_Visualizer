package com.scrumptious.algorithmvisualizer

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailValidator : UserInfoValidator {


    override fun validate(page: AppCompatActivity, email: String): Boolean {
        //checks if email matches the email format
        return if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            true
        } else {
            Toast.makeText(page,"Email is invalid", Toast.LENGTH_SHORT).show() //output message
            false
        }
    }
}