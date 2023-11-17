package com.scrumptious.algorithmvisualizer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityPasswordChangerBinding

class PasswordChanger: AppCompatActivity() {
    //Activity link to register page
    //TODO change the binding to the UI
    private lateinit var binding: ActivityPasswordChangerBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    var user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO add this line back in and change accordingly
        binding = ActivityPasswordChangerBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        //when click on the text occurs, run this
        //TODO add this line back in and change accordingly




        //when click on the login button occurs, run this
        //TODO add this line back in and change accordingly

        //change the email
        binding.button.setOnClickListener {
            val email = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            val newPassword = binding.newPasswordEditText.text.toString()
            val user = FirebaseAuth.getInstance().currentUser;
            // Get auth credentials from the user for re-authentication
            var credential = EmailAuthProvider.getCredential(email, pass); // Current Login Credentials

            // Prompt the user to re-provide their sign-in credentials
            // Prompt the user to re-provide their sign-in credentials
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (PasswordValidator().validate(this,newPassword))
                {
                    user?.reauthenticate(credential)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            user.updateEmail(newPassword).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Password Changed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this,Login::class.java)
                                    startActivity((intent))
                                }
                                else{
                                    val mess = task.exception?.message
                                    Toast.makeText(this, mess , Toast.LENGTH_SHORT).show() //output message

                                }
                            }


                        }
                        else{
                            Toast.makeText(this, "Failed to change password" , Toast.LENGTH_SHORT).show() //output message
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