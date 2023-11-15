package com.scrumptious.algorithmvisualizer

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PasswordValidator : UserInfoValidator{


    override fun validate(page: AppCompatActivity, pass: String): Boolean {
        var lengthCheck = false
        var uppercaseReq = false
        var lowercaseReq = false
        var numReq = false
        var valid = false
        lengthCheck = pass.length in 6..20;

        //go over every character in a password
        for (item in pass.indices){
            // check if it is a digit and mark the numeric requirement as true if so
            if (pass[item].isDigit())
            {
                numReq = true;
            }
            else
            {
                //check if the character is uppercase and mark the uppercase requirement if the character is uppercase and the requirement hasn't been met yet
                if (pass[item] == pass[item].uppercaseChar() && !uppercaseReq)
                {
                    uppercaseReq = true;
                }
                //check if the character is lowercase and mark the lowercase requirement if the character is lowercase and the requirement hasn't been met yet
                if (pass[item] == pass[item].lowercaseChar() && !lowercaseReq)
                {
                    lowercaseReq = true;
                }
            }
        }

        if (!lengthCheck){
            Toast.makeText(page,"Password should be between 6 and 20 characters long", Toast.LENGTH_SHORT).show() //output message
        }
        if (!uppercaseReq){
            Toast.makeText(page,"Password should contain at least one uppercase character", Toast.LENGTH_SHORT).show() //output message
        }
        if (!lowercaseReq){
            Toast.makeText(page,"Password should contain at least one lowercase character", Toast.LENGTH_SHORT).show() //output message
        }
        if (!numReq){
            Toast.makeText(page,"Password should contain at least one number", Toast.LENGTH_SHORT).show() //output message
        }
        return lengthCheck && uppercaseReq && lowercaseReq && numReq;
    }
}