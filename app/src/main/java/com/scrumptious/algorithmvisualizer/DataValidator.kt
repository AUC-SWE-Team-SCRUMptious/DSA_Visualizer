package com.scrumptious.algorithmvisualizer

//import android.provider.ContactsContract.Data
import java.lang.NumberFormatException

class DataValidator {
    var dataStatus: String = ""
    var validData = mutableListOf<Int>()
    fun validate( userInput: String){
        val myInts = userInput.split(",")
        val currentData = mutableListOf<Int>()
        var sorted = true
        for(i in myInts.indices){
            try {
                val num = myInts[i].trim().toLong() // will fail if input is not an integer
                if(num < -2147483648 || num > 2147483647){
                    dataStatus = "Input is out of range"
                    return
                }
                if(i != 0 && num.toInt() < currentData[i-1]){
                    sorted = false
                }
                currentData.add(num.toInt())
            }
            catch (e: NumberFormatException){
                dataStatus = "Input includes a non number"
                return
            }
        }
        val message = if (sorted) "sorted" else "not sorted" // Only sorted if ascending
        dataStatus = "Input is valid and $message"
        validData = currentData

    }
}
/*
Test Cases:

Input                           Expected Output                     Output
"-99, 99, 2147483648"           Input is out of range               Input is out of range
"-2147483648, 99, 2147483647"   Input is valid and sorted           Input is valid and sorted
"2147483647, 99, -2147483648"   Input is valid and not sorted       Input is valid and not sorted
"1,2,3"                         Input is valid and sorted           Input is valid and sorted
"3,2,1"                         Input is valid and not sorted       Input is valid and not sorted
"1"                             Input is valid and sorted           Input is valid and sorted
"a"                             Input includes a non number         Input includes a non number
"a,1,2"                         Input includes a non number         Input includes a non number
"1,a,2"                         Input includes a non number         Input includes a non number
"1,a2,3"                        Input includes a non number         Input includes a non number
"1,,2,3"                        Input includes a non number         Input includes a non number
 */