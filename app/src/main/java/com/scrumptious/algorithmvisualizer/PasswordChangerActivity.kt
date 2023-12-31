package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityPasswordChangerBinding

class PasswordChangerActivity: AppCompatActivity() {
    //Activity link to password changer

    private lateinit var binding: ActivityPasswordChangerBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    var user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordChangerBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()





        //go back to account manager
        binding.back.setOnClickListener{

            this.finish()
        }

        //change the password
        binding.button.setOnClickListener {
            val email = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            val newPassword = binding.newPasswordEditText.text.toString()
            val newPasswordConfirm = binding.newPasswordConfirmEditText.text.toString()
            val user = FirebaseAuth.getInstance().currentUser;
            // Get auth credentials from the user for re-authentication


            // Prompt the user to re-provide their sign-in credentials
            // Prompt the user to re-provide their sign-in credentials
            if (email.isNotEmpty() && pass.isNotEmpty() && newPassword.isNotEmpty()){
                val credential = EmailAuthProvider.getCredential(email, pass); // Current Login Credentials
                if (PasswordValidator().validate(this,newPassword))
                {
                    if (newPassword == newPasswordConfirm) {
                        user?.reauthenticate(credential)?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                //changes the password
                                user.updatePassword(newPassword).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Password Changed",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        val intent =
                                            Intent(this, AccountManagerActivity::class.java)
                                        startActivity((intent))
                                        this.finish()
                                    }
                                    else {
                                        val mess = task.exception?.message
                                        Toast.makeText(this, mess, Toast.LENGTH_SHORT)
                                            .show() //output message

                                    }
                                }


                            }
                            else {
                                Toast.makeText(
                                    this,
                                    "Incorrect email or password",
                                    Toast.LENGTH_SHORT
                                ).show() //output message
                            }
                        }


                    }

                }
                else
                {
                    Toast.makeText(this, "Passwords do not match" , Toast.LENGTH_SHORT).show() //output message
                }
            }
            else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show() //output message

            }
        }

    }
}