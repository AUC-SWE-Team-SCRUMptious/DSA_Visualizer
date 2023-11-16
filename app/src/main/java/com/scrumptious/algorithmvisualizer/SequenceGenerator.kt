package com.scrumptious.algorithmvisualizer

class SequenceGenerator {
    // Optionally we could choose to store the return in a property here in this class

    private lateinit var strategy : SequenceStrategy
    fun buildSequence(myData:Array<Int>) = strategy.BuildSequence(myData)

    fun setStrategy(newStrategy: SequenceStrategy) { strategy = newStrategy}

}