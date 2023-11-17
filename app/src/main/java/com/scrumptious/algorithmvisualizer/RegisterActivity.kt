package com.scrumptious.algorithmvisualizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scrumptious.algorithmvisualizer.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException

class RegisterActivity : AppCompatActivity() {
    //Activity link to register page
    private lateinit var binding: ActivityRegisterBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)

        //on click, go to login
        firebaseAuth= FirebaseAuth.getInstance()
        binding.textView2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //when any click occurs, run this
        binding.registerButton.setOnClickListener{
            val email = binding.usernameEditText.text.toString() //grabs the email
            val pass = binding.passwordEditText.text.toString() //grabs the pass
            //If both the email and password are inserted do the following
            if(email.isNotEmpty() && pass.isNotEmpty()){
                val passValidator = PasswordValidator() //password validator object
                val mailValidator = EmailValidator() //email validator
                if (passValidator.validate(this, pass) && mailValidator.validate(this, email)) //if the password and email are valid, do the following
                {
                    //sign up function
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show() //output message
                        //go to the login page
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity((intent))
                            }
                            else{
                                try {
                                    throw it.exception!!
                                }
                                catch(e: FirebaseAuthUserCollisionException) //exception if a user with the same email exists
                                {
                                    Toast.makeText(this, "A user with the email currently exists", Toast.LENGTH_SHORT).show() //output message
                                }
                                catch(e: FirebaseAuthWebException) //exception if no internet connection is present
                                {
                                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show() //output message
                                }
                                catch (e: FirebaseAuthInvalidCredentialsException){
                                    Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show() //output message
                                } //exception in case our email validator validates an invalid email address

                            }
                        }
                }





            }else{
                Toast.makeText(this,"Please insert both the email and password before continuing", Toast.LENGTH_SHORT).show() //output message
            }
        }
    }
}