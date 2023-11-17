package com.scrumptious.algorithmvisualizer

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class AnimationController : AppCompatActivity() {

    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

    // Array of pairs of arrays with the test data
    var dataArray: MutableList<Pair<Array<Int>, Array<Int>>> = mutableListOf(
        Pair(arrayOf(103, 35, 34, 23, 11), arrayOf(0, 1)),
        Pair(arrayOf(35, 103, 34, 23, 11), arrayOf(1, 2)),
        Pair(arrayOf(35, 34, 103, 23, 11), arrayOf(2, 3)),
        Pair(arrayOf(35, 34, 23, 103, 11), arrayOf(3, 4)),
        Pair(arrayOf(35, 34, 23, 11, 103), arrayOf(0, 1)),
        Pair(arrayOf(34, 35, 23, 11, 103), arrayOf(1, 2)),
        Pair(arrayOf(34, 23, 35, 11, 103), arrayOf(2, 3)),
        Pair(arrayOf(34, 23, 11, 35, 103), arrayOf(0, 1)),
        Pair(arrayOf(23, 34, 11, 35, 103), arrayOf(1, 2)),
        Pair(arrayOf(23, 11, 34, 35, 103), arrayOf(0, 1)),
        Pair(arrayOf(11, 23, 34, 35, 103), arrayOf())
    )

    private var dataIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        barChart = findViewById(R.id.barChart)

        // Start the initial update
        updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)

        // Set up a repeating handler to update the chart with a delay
        val handler = android.os.Handler()
        val delay: Long = 2000 // Delay in milliseconds

        handler.postDelayed(object : Runnable {
            override fun run() {
                // Move to the next set of values if available
                dataIndex++
                if (dataIndex < dataArray.size) {
                    // Update the chart with the next set of values
                    updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)

                    // Repeat the process
                    handler.postDelayed(this, delay)
                }
                else {
                    // Stop further updates when there's no more data
                    handler.removeCallbacks(this)
                }
            }
        }, delay)
    }

    private fun updateBarChart(values: Array<Int>, highlightIndices: Array<Int>) {
        getBarChartData(values)

        // Set color for all bars to the default color
        val defaultColor = resources.getColor(R.color.purple_200)
        val colors = mutableListOf<Int>()
        for (index in barEntriesList.indices) {
            colors.add(defaultColor)
        }

        // Set color for highlighted indices
        for (index in highlightIndices) {
            colors[index] = Color.RED
        }

        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        barDataSet.setColors(colors)

        barData = BarData(barDataSet)

        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barChart.description.isEnabled = false
        barChart.notifyDataSetChanged()
        barChart.invalidate()
    }

    private fun getBarChartData(values: Array<Int>) {
        barEntriesList = ArrayList()
        for ((index, value) in values.withIndex()) {
            barEntriesList.add(BarEntry(index.toFloat() + 1, value.toFloat()))
        }
    }
}
