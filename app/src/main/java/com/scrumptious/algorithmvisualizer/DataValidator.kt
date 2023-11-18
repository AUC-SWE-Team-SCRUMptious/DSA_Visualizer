package com.scrumptious.algorithmvisualizer

//import android.provider.ContactsContract.Data
import java.lang.NumberFormatException
// Does not accept " 1,2,,3"
class DataValidator {
    var dataStatus: String = ""
    var validData = mutableListOf<Int>()
    fun validate( userInput: String){
        if(userInput == ""){
            dataStatus = "Null input"
            return
        }
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
        if(currentData.size >20){
            dataStatus = "Input has more than 20 elements"
            return
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
""                              Null input		            Null input
"apples"                        Input includes a non number         Input includes a non number
"1,2,3,4,5,6,7,8,9,10,11,
12,13,14,15,16,17,18,19,20"     Input is valid and sorted           Input is valid and sorted
"1,2,3,4,5,6,7,8,9,10,11,12,
13,14,15,16,17,18,19,20,21"     Input has more than 20 elements     Input has more than 20 elements
 */


/* Driver code for testing
fun main(){

    val inputArrays = arrayOf(
        "-99, 99, 2147483648",
        "-2147483648, 99, 2147483647",
        "2147483647, 99, -2147483648",
       	"1,2,3" ,
        "3,2,1",
        "1",
        "a",
        "a,1,2",
        "1,a,2",
        "1,a2,3",
        "1,,2,3",
        "",
        "apples",
        "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20",
        "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21"
    )

    val i = DataValidator()
    for (str in inputArrays) {
        println("Input is: ")
        println(str)
        i.validate(str)
        println("Result:")
        println(i.DataStatus)
        println(i.ValidData)
        println()
    }
}
*/
