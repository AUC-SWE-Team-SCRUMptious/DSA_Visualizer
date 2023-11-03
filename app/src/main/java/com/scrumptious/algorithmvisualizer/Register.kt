package com.scrumptious.algorithmvisualizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.scrumptious.algorithmvisualizer.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.textView2.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener{
            val email = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty()){
                val passValidator = PasswordValidator(pass)
                val mailValidator = EmailValidator(email)
                if (passValidator.valid && mailValidator.valid)
                {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){

                            val intent = Intent(this,Login::class.java)
                            startActivity((intent))
                        }
                    }
                }
                else{
                    if (!passValidator.lengthCheck){
                        Toast.makeText(this,"Password should be between 6 and 20 characters long", Toast.LENGTH_SHORT).show()
                    }
                    if (!passValidator.uppercaseReq){
                        Toast.makeText(this,"Password should contain at least one uppercase character", Toast.LENGTH_SHORT).show()
                    }
                    if (!passValidator.lowercaseReq){
                        Toast.makeText(this,"Password should contain at least one lowercase character", Toast.LENGTH_SHORT).show()
                    }
                    if (!passValidator.numReq){
                        Toast.makeText(this,"Password should contain at least one number", Toast.LENGTH_SHORT).show()
                    }
                    if (!mailValidator.valid){
                        Toast.makeText(this,"Email is invalid", Toast.LENGTH_SHORT).show()
                    }

                }




            }else{
                Toast.makeText(this,"Please insert both the email and password before continuing", Toast.LENGTH_SHORT).show()
            }
        }
    }
}