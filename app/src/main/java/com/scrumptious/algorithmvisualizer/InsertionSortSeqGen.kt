package com.scrumptious.algorithmvisualizer

class InsertionSortSeqGen{
    var result = mutableListOf<Pair<Array<Int>, Array<Int>>>()
    fun generateSeq(arr: Array<Int>){
        result = mutableListOf()
        result.add( Pair(arr.copyOf(), emptyArray()))

        if(arr.isEmpty()) return

        for(i in 1..<arr.size){
            var j = i

            while (j > 0 && arr[j] < arr[j - 1]) {
                //swap(arr, j, j - 1)
                val temp = arr[j]
                arr[j] = arr[j-1]
                arr[j-1] = temp
                result.add(Pair(arr.copyOf(), arrayOf(j,j-1)))
                j--
            }
        }

    }
}

/*
Driver Code for tests cases:
fun main() {
    val i = InsertionSortSeqGen()
    var inp = readln()
    while(inp!="*"){
        val values = inp.split(",")
        val intArray = values.map { it.toInt() }.toTypedArray()
        i.generateSeq(intArray)
        val listOfPairs = i.result
        for ((firstArray, secondArray) in listOfPairs) {
            println("First Array: ${firstArray.joinToString(", ")}")
            println("Second Array: ${secondArray.joinToString(", ")}")
            println()
        }

        inp = readln()
    }
}

Test Cases:

Input:
1,2,3

Output:
First Array: 1, 2, 3
Second Array:

Input:
1,3,2

Output
First Array: 1, 3, 2
Second Array:

First Array: 1, 2, 3
Second Array: 2, 1

Input:
2,3,1

Output:
First Array: 2, 3, 1
Second Array:

First Array: 2, 1, 3
Second Array: 2, 1

First Array: 1, 2, 3
Second Array: 1, 0

Input:
1

Output:
First Array: 1
Second Array:

*/