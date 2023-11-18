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
    //Activity link to account manager page
    private lateinit var binding: ActivityAccountManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountManagerBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)
        //goes to email changing (actually broken)
        binding.changeEmail.setOnClickListener {
            val intent = Intent(this, EmailChangerActivity::class.java)
            startActivity(intent)

        }
        //goes to password changing
        binding.changePassword.setOnClickListener {
            val intent = Intent(this, PasswordChangerActivity::class.java)
            startActivity(intent)

        }
        //goes to account deletion
        binding.accountDelete.setOnClickListener {
            val intent = Intent(this, AccountDeleterActivity::class.java)
            startActivity(intent)

        }
        //sign out
        //TODO add cases for when the user is null
        binding.signOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            if (FirebaseAuth.getInstance().currentUser != null)
            {
                Firebase.auth.signOut()
                this.finish()

            }
            else
            {
                Toast.makeText(this, "You shouldn't even be here" , Toast.LENGTH_SHORT).show() //output message
            }
        }

    }
}