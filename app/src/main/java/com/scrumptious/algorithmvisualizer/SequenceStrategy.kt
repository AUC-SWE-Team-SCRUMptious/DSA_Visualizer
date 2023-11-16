package com.scrumptious.algorithmvisualizer

interface SequenceStrategy {
    fun BuildSequence(myData: Array<Int>) : MutableList<Pair<Array<Int>, Array<Int>>>
}


