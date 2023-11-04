package com.scrumptious.algorithmvisualizer

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
        val results = selectionSortWithStepsAndSwaps(inputArray.copyOf())

        println("Original Array ${index + 1}: ${inputArray.joinToString(", ")}")
        for ((stepIndex, pair) in results.withIndex()) {
            val (step, swapIndices) = pair
            val swapText = if (swapIndices.isNotEmpty()) "Swapped indices: ${swapIndices.joinToString(", ")}" else "No swap in this step"
            println("Step ${stepIndex + 1}: ${step.joinToString(", ")} - $swapText")
        }


        println()
    }
}


