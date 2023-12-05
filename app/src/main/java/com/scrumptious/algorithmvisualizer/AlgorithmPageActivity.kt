package com.scrumptious.algorithmvisualizer


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.scrumptious.algorithmvisualizer.databinding.ActivityAlgorithmPageBinding
class AlgorithmPageActivity: AppCompatActivity() {

    //Activity link to algorithm page
    private lateinit var binding: ActivityAlgorithmPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlgorithmPageBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)

        //go back to select screen
        /*
        binding.back.setOnClickListener{
            this.finish()
        }

         */


        //delete the account
        /*
        binding.button.setOnClickListener {

            val intent = Intent(this, AnimationControllerActivity::class.java)
            startActivity(intent)


        }

         */
    }



}