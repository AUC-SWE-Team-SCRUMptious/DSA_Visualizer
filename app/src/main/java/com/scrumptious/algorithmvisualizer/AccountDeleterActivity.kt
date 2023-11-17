package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityAccountDeleterBinding

class AccountDeleterActivity: AppCompatActivity() {
    //Activity link to account deletion page
    private lateinit var binding: ActivityAccountDeleterBinding

    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountDeleterBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()


        //delete the account
        binding.button.setOnClickListener {
            //get the email and password
            val email = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

            //user object
            val user = FirebaseAuth.getInstance().currentUser;
            // Get auth credentials from the user for re-authentication
            var credential =
                EmailAuthProvider.getCredential(email, pass); // Current Login Credentials

            // Prompt the user to re-provide their sign-in credentials
            // Prompt the user to re-provide their sign-in credentials
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                    //reauthenticates the user
                    user?.reauthenticate(credential)?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            //deletes the account and takes them back to the log in page
                            user.delete()
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity((intent))
                                }
                        else {
                            Toast.makeText(this, "Failed to delete account", Toast.LENGTH_SHORT)
                                .show() //output message
                        }

                    }

            }
            //in case of empty fields
            else {
                Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT)
                    .show() //output message
            }



            }
        }

    }