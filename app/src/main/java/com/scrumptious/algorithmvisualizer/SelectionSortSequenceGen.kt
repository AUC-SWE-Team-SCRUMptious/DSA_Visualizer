package com.scrumptious.algorithmvisualizer


class SelectionSortSequenceGen {
fun selectionSortSequenceGenerator(arr: Array<Int>): Array<Pair<Array<Int>, Array<Int>>> {
    val steps = mutableListOf(arr.copyOf()) // Initialize the steps array with the initial state
    val result = mutableListOf<Pair<Array<Int>, Array<Int>>>() // Initialize the result list

    if (arr.isEmpty()) {
        return result.toTypedArray() // Return an empty result for an empty input array
    }

    for (i in 0 until arr.size - 1) {
        var minIndex = i
        val swaps = mutableListOf<Int>()

        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j
            }
        }

        if (minIndex != i) {
            val temp = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = temp

            swaps.add(i)
            swaps.add(minIndex)
        }

        steps.add(arr.copyOf()) // Store the current state in the steps array
        result.add(Pair(steps.last().copyOf(), swaps.toTypedArray()))
    }

    return result.toTypedArray()
}

}
/*
//main fun to test the algorithm
fun main() {
    val inputArray1 = arrayOf(64, 25, 12, 22, 11)
    val inputArray2 = arrayOf(5, 4, 3, 2, 1)
    val inputArray3 = arrayOf(1, 2, 3, 4, 5)
    val inputArray4 = arrayOf(10, 20, 30, 40, 50, 60)

    val inputArray5 = arrayOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
    val inputArray6 = arrayOf(7, 6, 5, 4, 3, 2, 1)
    val inputArray7 = arrayOf(1)
    val inputArray8 = emptyArray<Int>()

    val arrays = listOf(inputArray1, inputArray2, inputArray3, inputArray4, inputArray5, inputArray6, inputArray7, inputArray8)

    for ((index, inputArray) in arrays.withIndex()) {
        val results = selectionSortSequenceGenerator(inputArray.copyOf())

        println("Original Array ${index + 1}: ${inputArray.joinToString(", ")}")
        for ((stepIndex, pair) in results.withIndex()) {
            val (step, swapIndices) = pair
            val swapText = if (swapIndices.isNotEmpty()) "Swapped indices: ${swapIndices.joinToString(", ")}" else "No swap in this step"
            println("Step ${stepIndex + 1}: ${step.joinToString(", ")} - $swapText")
        }


        println()
    }
}
//Correct Output:
* Original Array 1: 64, 25, 12, 22, 11
Step 1: 11, 25, 12, 22, 64 - Swapped indices: 0, 4
Step 2: 11, 12, 25, 22, 64 - Swapped indices: 1, 2
Step 3: 11, 12, 22, 25, 64 - Swapped indices: 2, 3
Step 4: 11, 12, 22, 25, 64 - No swap in this step

Original Array 2: 5, 4, 3, 2, 1
Step 1: 1, 4, 3, 2, 5 - Swapped indices: 0, 4
Step 2: 1, 2, 3, 4, 5 - Swapped indices: 1, 3
Step 3: 1, 2, 3, 4, 5 - No swap in this step
Step 4: 1, 2, 3, 4, 5 - No swap in this step

Original Array 3: 1, 2, 3, 4, 5
Step 1: 1, 2, 3, 4, 5 - No swap in this step
Step 2: 1, 2, 3, 4, 5 - No swap in this step
Step 3: 1, 2, 3, 4, 5 - No swap in this step
Step 4: 1, 2, 3, 4, 5 - No swap in this step

Original Array 4: 10, 20, 30, 40, 50, 60
Step 1: 10, 20, 30, 40, 50, 60 - No swap in this step
Step 2: 10, 20, 30, 40, 50, 60 - No swap in this step
Step 3: 10, 20, 30, 40, 50, 60 - No swap in this step
Step 4: 10, 20, 30, 40, 50, 60 - No swap in this step
Step 5: 10, 20, 30, 40, 50, 60 - No swap in this step

Original Array 5: 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5
Step 1: 1, 3, 4, 1, 5, 9, 2, 6, 5, 3, 5 - Swapped indices: 0, 1
Step 2: 1, 1, 4, 3, 5, 9, 2, 6, 5, 3, 5 - Swapped indices: 1, 3
Step 3: 1, 1, 2, 3, 5, 9, 4, 6, 5, 3, 5 - Swapped indices: 2, 6
Step 4: 1, 1, 2, 3, 5, 9, 4, 6, 5, 3, 5 - No swap in this step
Step 5: 1, 1, 2, 3, 3, 9, 4, 6, 5, 5, 5 - Swapped indices: 4, 9
Step 6: 1, 1, 2, 3, 3, 4, 9, 6, 5, 5, 5 - Swapped indices: 5, 6
Step 7: 1, 1, 2, 3, 3, 4, 5, 6, 9, 5, 5 - Swapped indices: 6, 8
Step 8: 1, 1, 2, 3, 3, 4, 5, 5, 9, 6, 5 - Swapped indices: 7, 9
Step 9: 1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9 - Swapped indices: 8, 10
Step 10: 1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9 - No swap in this step

Original Array 6: 7, 6, 5, 4, 3, 2, 1
Step 1: 1, 6, 5, 4, 3, 2, 7 - Swapped indices: 0, 6
Step 2: 1, 2, 5, 4, 3, 6, 7 - Swapped indices: 1, 5
Step 3: 1, 2, 3, 4, 5, 6, 7 - Swapped indices: 2, 4
Step 4: 1, 2, 3, 4, 5, 6, 7 - No swap in this step
Step 5: 1, 2, 3, 4, 5, 6, 7 - No swap in this step
Step 6: 1, 2, 3, 4, 5, 6, 7 - No swap in this step

Original Array 7: 1

Original Array 8:

*
*
* */

