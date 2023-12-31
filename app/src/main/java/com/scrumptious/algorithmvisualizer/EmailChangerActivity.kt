package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityEmailChangerBinding


class EmailChangerActivity: AppCompatActivity() {
    //Activity link to register page
    //TODO change the binding to the UI
    private lateinit var binding: ActivityEmailChangerBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmailChangerBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()




        //go back to account manager
        binding.back.setOnClickListener{
            this.finish()
        }


        //change the email on button click
        binding.button.setOnClickListener {
            val email = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            val newEmail = binding.newEmailEditText.text.toString()
            val user = FirebaseAuth.getInstance().currentUser;
            // Get auth credentials from the user for re-authentication


            // Prompt the user to re-provide their sign-in credentials
            // Prompt the user to re-provide their sign-in credentials
            if (email.isNotEmpty() && pass.isNotEmpty() && newEmail.isNotEmpty()) {
                val credential = EmailAuthProvider.getCredential(email, pass); // Current Login Credentials
                if (EmailValidator().validate(this,newEmail))
                {
                    user?.reauthenticate(credential)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            user.verifyBeforeUpdateEmail(newEmail).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Check your email for change confirmation",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this,AccountManagerActivity::class.java)
                                    startActivity((intent))
                                    this.finish()
                                }
                                else{
                                    val mess = task.exception?.message
                                    Toast.makeText(this, mess , Toast.LENGTH_SHORT).show() //output message

                                }
                            }


                        }
                        else{
                            Toast.makeText(this, "Incorrect email or password" , Toast.LENGTH_SHORT).show() //output message
                        }
                    }


                    }

                }
                else {
                    Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show() //output message

                }
            }

        }





    }


