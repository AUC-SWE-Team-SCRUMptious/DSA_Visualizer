package com.scrumptious.algorithmvisualizer

interface SequenceStrategy {
    fun buildSequence(myData: Array<Int>) : MutableList<Pair<Array<Int>, Array<Int>>>
}


