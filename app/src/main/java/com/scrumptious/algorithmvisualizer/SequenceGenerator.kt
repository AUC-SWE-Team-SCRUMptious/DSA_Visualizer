package com.scrumptious.algorithmvisualizer

class SequenceGenerator {
    // Optionally we could choose to store the return in a property here in this class

    //Canonical implementation would have required the following:
    //private lateinit var strategy : SequenceStrategy
    //fun setStrategy(newStrategy: SequenceStrategy) { strategy = newStrategy}
    //However, to avoid the possibility of an uninitialized strategy call being used it has become:
    fun buildSequence(strategy : SequenceStrategy, myData:Array<Int>) = strategy.buildSequence(myData)


}