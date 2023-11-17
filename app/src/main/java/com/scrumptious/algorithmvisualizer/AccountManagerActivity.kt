package com.scrumptious.algorithmvisualizer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scrumptious.algorithmvisualizer.databinding.ActivityAccountManagerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountManagerActivity: AppCompatActivity() {
    //Activity link to register page
    private lateinit var binding: ActivityAccountManagerBinding
    //Firebase authenticator object
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountManagerBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)

        binding.changeEmail.setOnClickListener {
            val intent = Intent(this, EmailChangerActivity::class.java)
            startActivity(intent)
        }
        binding.changePassword.setOnClickListener {
            val intent = Intent(this, PasswordChangerActivity::class.java)
            startActivity(intent)
        }
        binding.signOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            if (FirebaseAuth.getInstance().currentUser != null)
            {
                Firebase.auth.signOut()

            }
        }

    }
}