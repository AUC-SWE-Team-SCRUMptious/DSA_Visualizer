package com.scrumptious.algorithmvisualizer


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scrumptious.algorithmvisualizer.databinding.ActivityBubbleSortPageBinding

class BubbleSortPageActivity: AppCompatActivity() {

    //Activity link to algorithm page
    private lateinit var binding: ActivityBubbleSortPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBubbleSortPageBinding.inflate(layoutInflater)
        //links view to class
        setContentView(binding.root)

        //go back to select screen

        binding.start.setOnClickListener{
            val dataValidator = DataValidator()
            dataValidator.validate(binding.dataInput.text.toString())
            // check data status
            // if Its "Input is valid and sorted" then warn user that its already sorted
            // else if its "Input is valid and not sorted" then proceed to next activity
            // else if its "Input includes a non number" then warn user that input includes a non number
            // else if its "Input is out of range" then warn user that input is out of range
            // else if its "Input has more than 20 elements" then warn user that input has more than 20 elements
            // else if its "Null input" then warn user that input is null

            if(dataValidator.dataStatus == "Input is valid and sorted"){
                Toast.makeText(this, "Input is already sorted", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(dataValidator.dataStatus == "Input is valid and not sorted"){
                // To pass data to sequence generator use the following
                val seqGen = SequenceGenerator()
                // Sequence strategy is the abstract interface
                // for each strategy call a specific object of its generator
                // for example

                val myList: MutableList<Pair<Array<Int>, Array<Int>>> = seqGen.buildSequence(BubbleSort(),dataValidator.validData.toTypedArray())// your data here

                val intent = Intent(this, AnimationControllerActivity::class.java)
                intent.putExtra("dataKey", ArrayList(myList)) // ArrayList is parcelable

                startActivity(intent)
            }
            else if(dataValidator.dataStatus == "Input includes a non number"){
                Toast.makeText(this, "Input includes a non number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(dataValidator.dataStatus == "Input is out of range"){
                Toast.makeText(this, "Input is out of range", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(dataValidator.dataStatus == "Input has more than 20 elements"){
                Toast.makeText(this, "Input has more than 20 elements", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(dataValidator.dataStatus == "Null input"){
                Toast.makeText(this, "Null input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }




        }





        binding.back.setOnClickListener {

            this.finish()


        }

    }



}