package com.scrumptious.algorithmvisualizer

fun bubbleSortSequenceGenerator(inputArr: Array<Int>): Array<Pair<Array<Int>, Array<Int>>> {
    val n = inputArr.size
    val sortArr = inputArr.copyOf()
    val stepsAndSwaps = mutableListOf<Pair<Array<Int>, Array<Int>>>()

    for (i in 0 until n - 1) {
        var swapped = false
        for (j in 0 until n - i - 1) {
            if (sortArr[j] > sortArr[j + 1]) {
                val temp = sortArr[j]
                sortArr[j] = sortArr[j + 1]
                sortArr[j + 1] = temp
                val indices = arrayOf(j, j + 1)
                stepsAndSwaps.add(Pair(sortArr.copyOf(), indices))
                swapped = true
            }
        }

        if (!swapped) {
            break
        }
    }

    return stepsAndSwaps.toTypedArray()
}

fun displaySteps(inputArr: Array<Int>, steps: Array<Pair<Array<Int>, Array<Int>>>) {
    println("Original Array: ${inputArr.joinToString()}")
    for ((i, step) in steps.withIndex()) {
        val (swappedArr, swappedIndices) = step
        println("Step ${i + 1}: ${swappedArr.joinToString()}, Indices Swapped: ${swappedIndices.joinToString()}")
    }
}

/*
Driver Code to test function
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
        val stepsAndSwaps = bubbleSortSequenceGenerator(inputArr)
        displaySteps(inputArr, stepsAndSwaps)
        println()
    }
}*/
