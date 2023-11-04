package com.scrumptious.algorithmvisualizer

class BubbleSortSequenceGenerator{
    var result = mutableListOf<Pair<Array<Int>, Array<Int>>>()

    fun sequenceGenerator(inputArr: Array<Int>){
        val n = inputArr.size
        val sortArr = inputArr.copyOf()

        for (i in 0 until n - 1) {
            var swapped = false
            for (j in 0 until n - i - 1) {
                if (sortArr[j] > sortArr[j + 1]) {
                    val temp = sortArr[j]
                    sortArr[j] = sortArr[j + 1]
                    sortArr[j + 1] = temp
                    val indices = arrayOf(j, j + 1)
                    result.add(Pair(sortArr.copyOf(), indices))
                    swapped = true
                }
            }
            if (!swapped) {
                break
            }
        }
    }
}

/*
fun displaySteps(inputArr: Array<Int>, steps: MutableList<Pair<Array<Int>, Array<Int>>>) {
    println("Original Array: ${inputArr.joinToString()}")
    for ((i, step) in steps.withIndex()) {
        val (swappedArr, swappedIndices) = step
        println("Step ${i + 1}: ${swappedArr.joinToString()}, Indices Swapped: ${swappedIndices.joinToString()}")
    }
}

fun main() {
    val inputArrays = arrayOf(
        arrayOf(5, 6, 7, 1, 1),
        arrayOf(43, 32, 23, 54, 55),
        arrayOf(5, 7, 10, 8, 3),
        arrayOf(1, 2, 3, 4, 5),
        arrayOf(103, 35, 34, 23, 11),
        arrayOf(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    )

    for (inputArr in inputArrays) {
        val i = BubbleSortSequenceGenerator()
        i.sequenceGenerator(inputArr)
        displaySteps(inputArr, i.result)
        println()
    }
}


Test Cases:

Original Array: 5, 6, 7, 1, 1
Step 1: 5, 6, 1, 7, 1, Indices Swapped: 2, 3
Step 2: 5, 6, 1, 1, 7, Indices Swapped: 3, 4
Step 3: 5, 1, 6, 1, 7, Indices Swapped: 1, 2
Step 4: 5, 1, 1, 6, 7, Indices Swapped: 2, 3
Step 5: 1, 5, 1, 6, 7, Indices Swapped: 0, 1
Step 6: 1, 1, 5, 6, 7, Indices Swapped: 1, 2

Original Array: 43, 32, 23, 54, 55
Step 1: 32, 43, 23, 54, 55, Indices Swapped: 0, 1
Step 2: 32, 23, 43, 54, 55, Indices Swapped: 1, 2
Step 3: 23, 32, 43, 54, 55, Indices Swapped: 0, 1

Original Array: 5, 7, 10, 8, 3
Step 1: 5, 7, 8, 10, 3, Indices Swapped: 2, 3
Step 2: 5, 7, 8, 3, 10, Indices Swapped: 3, 4
Step 3: 5, 7, 3, 8, 10, Indices Swapped: 2, 3
Step 4: 5, 3, 7, 8, 10, Indices Swapped: 1, 2
Step 5: 3, 5, 7, 8, 10, Indices Swapped: 0, 1

Original Array: 1, 2, 3, 4, 5

Original Array: 103, 35, 34, 23, 11
Step 1: 35, 103, 34, 23, 11, Indices Swapped: 0, 1
Step 2: 35, 34, 103, 23, 11, Indices Swapped: 1, 2
Step 3: 35, 34, 23, 103, 11, Indices Swapped: 2, 3
Step 4: 35, 34, 23, 11, 103, Indices Swapped: 3, 4
Step 5: 34, 35, 23, 11, 103, Indices Swapped: 0, 1
Step 6: 34, 23, 35, 11, 103, Indices Swapped: 1, 2
Step 7: 34, 23, 11, 35, 103, Indices Swapped: 2, 3
Step 8: 23, 34, 11, 35, 103, Indices Swapped: 0, 1
Step 9: 23, 11, 34, 35, 103, Indices Swapped: 1, 2
Step 10: 11, 23, 34, 35, 103, Indices Swapped: 0, 1
*/