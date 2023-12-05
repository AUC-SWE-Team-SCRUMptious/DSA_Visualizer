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

        binding.generateSequence.setOnClickListener{
            val datavalidator_instance = DataValidator()
            datavalidator_instance.validate(/*Input data to be tested*/)
            // check data status
            // if Its "Input is valid and sorted" then warn user that its already sorted
            // else if its "Input is valid and not sorted" then proceed to next activity
            // else if its "Input includes a non number" then warn user that input includes a non number
            // else if its "Input is out of range" then warn user that input is out of range
            // else if its "Input has more than 20 elements" then warn user that input has more than 20 elements
            // else if its "Null input" then warn user that input is null

            // To pass data to sequence generator use the following
            val seqGen = SequenceGenerator()
            // Sequence strategy is the abstract interface
            // for each strategy call a specific object of its generattor
            // for example

            val myList: MutableList<Pair<Array<Int>, Array<Int>>> = seqGen.buildSequence(BubbleSort(),datavalidator_instance.validData)// your data here

            val intent = Intent(this, AnimationControllerActivity::class.java)
            intent.putExtra("dataKey", ArrayList(myList)) // ArrayList is parcelable

            startActivity(intent)


        }





        binding.back.setOnClickListener {

            this.finish()


        }

    }



}