package com.scrumptious.algorithmvisualizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scrumptious.algorithmvisualizer.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException

class Register : AppCompatActivity() {
    //Activity link to register page
    private lateinit var binding: ActivityRegisterBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        /*
        binding.textView2.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        TODO:Return the code when login is implemented
         */

        //when any click occurs, run this
        binding.registerButton.setOnClickListener{
            val email = binding.usernameEditText.text.toString() //grabs the email
            val pass = binding.passwordEditText.text.toString() //grabs the pass
            //If both the email and password are inserted do the following
            if(email.isNotEmpty() && pass.isNotEmpty()){
                val passValidator = PasswordValidator(pass) //password validator object
                val mailValidator = EmailValidator(email) //email validator
                if (passValidator.valid && mailValidator.valid) //if the password and email are valid, do the following
                {

                        Toast.makeText(this,"This combination is valid",Toast.LENGTH_SHORT).show() //output message
                }
                else{
                    if (!passValidator.lengthCheck){
                        Toast.makeText(this,"Password should be between 6 and 20 characters long", Toast.LENGTH_SHORT).show() //output message
                    }
                    if (!passValidator.uppercaseReq){
                        Toast.makeText(this,"Password should contain at least one uppercase character", Toast.LENGTH_SHORT).show() //output message
                    }
                    if (!passValidator.lowercaseReq){
                        Toast.makeText(this,"Password should contain at least one lowercase character", Toast.LENGTH_SHORT).show() //output message
                    }
                    if (!passValidator.numReq){
                        Toast.makeText(this,"Password should contain at least one number", Toast.LENGTH_SHORT).show() //output message
                    }
                    if (!mailValidator.valid){
                        Toast.makeText(this,"Email is invalid", Toast.LENGTH_SHORT).show() //output message
                    }

                }




            }else{
                Toast.makeText(this,"Please insert both the email and password before continuing", Toast.LENGTH_SHORT).show() //output message
            }
        }
    }
}