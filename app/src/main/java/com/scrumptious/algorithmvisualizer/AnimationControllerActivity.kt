package com.scrumptious.algorithmvisualizer

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.util.ArrayList

class AnimationControllerActivity: AppCompatActivity() {
    private lateinit var barChart: BarChart
    private lateinit var barData: BarData
    private lateinit var barDataSet: BarDataSet
    private lateinit var barEntriesList: ArrayList<BarEntry>

    val dataArray = intent.getIntegerArrayListExtra("dataKey") // ArrayList is parcelable

    /*var dataArray: MutableList<Pair<Array<Int>, Array<Int>>> = mutableListOf(
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
        Pair(arrayOf(11, 23, 34, 35, 103), emptyArray())
    )*/
    /*
    var dataArray: MutableList<Pair<Array<Int>, Array<Int>>> = mutableListOf(
        Pair(arrayOf(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(0, 1)),
        Pair(arrayOf(19, 20, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(1, 2)),
        Pair(arrayOf(19, 18, 20, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(2, 3)),
        Pair(arrayOf(19, 18, 17, 20, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(3, 4)),
        Pair(arrayOf(19, 18, 17, 16, 20, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(4, 5)),
        Pair(arrayOf(19, 18, 17, 16, 15, 20, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(5, 6)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 20, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(6, 7)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 20, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(7, 8)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 20, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(8, 9)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(9, 10)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 20, 9, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(10, 11)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 20, 8, 7, 6, 5, 4, 3, 2, 1), arrayOf(11, 12)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 20, 7, 6, 5, 4, 3, 2, 1), arrayOf(12, 13)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 20, 6, 5, 4, 3, 2, 1), arrayOf(13, 14)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 20, 5, 4, 3, 2, 1), arrayOf(14, 15)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 20, 4, 3, 2, 1), arrayOf(15, 16)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 20, 3, 2, 1), arrayOf(16, 17)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 20, 2, 1), arrayOf(17, 18)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 20, 1), arrayOf(18, 19)),
        Pair(arrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(0, 1)),
        Pair(arrayOf(18, 19, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(1, 2)),
        Pair(arrayOf(18, 17, 19, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(2, 3)),
        Pair(arrayOf(18, 17, 16, 19, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(3, 4)),
        Pair(arrayOf(18, 17, 16, 15, 19, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(4, 5)),
        Pair(arrayOf(18, 17, 16, 15, 14, 19, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(5, 6)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 19, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(6, 7)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 19, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(7, 8)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 19, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(8, 9)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 19, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(9, 10)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 19, 8, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(10, 11)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 19, 7, 6, 5, 4, 3, 2, 1, 20), arrayOf(11, 12)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 19, 6, 5, 4, 3, 2, 1, 20), arrayOf(12, 13)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 19, 5, 4, 3, 2, 1, 20), arrayOf(13, 14)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 19, 4, 3, 2, 1, 20), arrayOf(14, 15)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 19, 3, 2, 1, 20), arrayOf(15, 16)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 19, 2, 1, 20), arrayOf(16, 17)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 19, 1, 20), arrayOf(17, 18)),
        Pair(arrayOf(18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(17, 18, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(17, 16, 18, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(17, 16, 15, 18, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(17, 16, 15, 14, 18, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(17, 16, 15, 14, 13, 18, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 18, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 18, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 18, 9, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 18, 8, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 18, 7, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 18, 6, 5, 4, 3, 2, 1, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 18, 5, 4, 3, 2, 1, 19, 20), arrayOf(12, 13)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 18, 4, 3, 2, 1, 19, 20), arrayOf(13, 14)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 18, 3, 2, 1, 19, 20), arrayOf(14, 15)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 18, 2, 1, 19, 20), arrayOf(15, 16)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 18, 1, 19, 20), arrayOf(16, 17)),
        Pair(arrayOf(17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(16, 17, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(16, 15, 17, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(16, 15, 14, 17, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(16, 15, 14, 13, 17, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(16, 15, 14, 13, 12, 17, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 17, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 17, 9, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 17, 8, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 17, 7, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 17, 6, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 17, 5, 4, 3, 2, 1, 18, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 17, 4, 3, 2, 1, 18, 19, 20), arrayOf(12, 13)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 17, 3, 2, 1, 18, 19, 20), arrayOf(13, 14)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 17, 2, 1, 18, 19, 20), arrayOf(14, 15)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 17, 1, 18, 19, 20), arrayOf(15, 16)),
        Pair(arrayOf(16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(15, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(15, 14, 16, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(15, 14, 13, 16, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(15, 14, 13, 12, 16, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(15, 14, 13, 12, 11, 16, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 16, 9, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 16, 8, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 16, 7, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 16, 6, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 16, 5, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 16, 4, 3, 2, 1, 17, 18, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 16, 3, 2, 1, 17, 18, 19, 20), arrayOf(12, 13)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 16, 2, 1, 17, 18, 19, 20), arrayOf(13, 14)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 16, 1, 17, 18, 19, 20), arrayOf(14, 15)),
        Pair(arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(14, 15, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(14, 13, 15, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(14, 13, 12, 15, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(14, 13, 12, 11, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(14, 13, 12, 11, 10, 15, 9, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 15, 8, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 15, 7, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 15, 6, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 15, 5, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 15, 4, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 15, 3, 2, 1, 16, 17, 18, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 15, 2, 1, 16, 17, 18, 19, 20), arrayOf(12, 13)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 15, 1, 16, 17, 18, 19, 20), arrayOf(13, 14)),
        Pair(arrayOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(13, 14, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(13, 12, 14, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(13, 12, 11, 14, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(13, 12, 11, 10, 14, 9, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(13, 12, 11, 10, 9, 14, 8, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 14, 7, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 14, 6, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 14, 5, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 14, 4, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 14, 3, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 14, 2, 1, 15, 16, 17, 18, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 14, 1, 15, 16, 17, 18, 19, 20), arrayOf(12, 13)),
        Pair(arrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(12, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(12, 11, 13, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(12, 11, 10, 13, 9, 8, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(12, 11, 10, 9, 13, 8, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(12, 11, 10, 9, 8, 13, 7, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 13, 6, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 13, 5, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 5, 13, 4, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 5, 4, 13, 3, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 13, 2, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 13, 1, 14, 15, 16, 17, 18, 19, 20), arrayOf(11, 12)),
        Pair(arrayOf(12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(11, 12, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(11, 10, 12, 9, 8, 7, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(11, 10, 9, 12, 8, 7, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(11, 10, 9, 8, 12, 7, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(11, 10, 9, 8, 7, 12, 6, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 12, 5, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 5, 12, 4, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 5, 4, 12, 3, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 12, 2, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 12, 1, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(10, 11)),
        Pair(arrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(10, 11, 9, 8, 7, 6, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(10, 9, 11, 8, 7, 6, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(10, 9, 8, 11, 7, 6, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(10, 9, 8, 7, 11, 6, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(10, 9, 8, 7, 6, 11, 5, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(10, 9, 8, 7, 6, 5, 11, 4, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(10, 9, 8, 7, 6, 5, 4, 11, 3, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 11, 2, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 11, 1, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(9, 10)),
        Pair(arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(9, 10, 8, 7, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(9, 8, 10, 7, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(9, 8, 7, 10, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(9, 8, 7, 6, 10, 5, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(9, 8, 7, 6, 5, 10, 4, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(9, 8, 7, 6, 5, 4, 10, 3, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(9, 8, 7, 6, 5, 4, 3, 10, 2, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(9, 8, 7, 6, 5, 4, 3, 2, 10, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(8, 9)),
        Pair(arrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(8, 9, 7, 6, 5, 4, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(8, 7, 9, 6, 5, 4, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(8, 7, 6, 9, 5, 4, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(8, 7, 6, 5, 9, 4, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(8, 7, 6, 5, 4, 9, 3, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(8, 7, 6, 5, 4, 3, 9, 2, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(8, 7, 6, 5, 4, 3, 2, 9, 1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(7, 8)),
        Pair(arrayOf(8, 7, 6, 5, 4, 3, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(7, 8, 6, 5, 4, 3, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(7, 6, 8, 5, 4, 3, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(7, 6, 5, 8, 4, 3, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(7, 6, 5, 4, 8, 3, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(7, 6, 5, 4, 3, 8, 2, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(7, 6, 5, 4, 3, 2, 8, 1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(6, 7)),
        Pair(arrayOf(7, 6, 5, 4, 3, 2, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(6, 7, 5, 4, 3, 2, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(6, 5, 7, 4, 3, 2, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(6, 5, 4, 7, 3, 2, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(6, 5, 4, 3, 7, 2, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(6, 5, 4, 3, 2, 7, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(5, 6)),
        Pair(arrayOf(6, 5, 4, 3, 2, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(5, 6, 4, 3, 2, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(5, 4, 6, 3, 2, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(5, 4, 3, 6, 2, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(5, 4, 3, 2, 6, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(4, 5)),
        Pair(arrayOf(5, 4, 3, 2, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(4, 5, 3, 2, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(4, 3, 5, 2, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(4, 3, 2, 5, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(3, 4)),
        Pair(arrayOf(4, 3, 2, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(3, 4, 2, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(3, 2, 4, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(2, 3)),
        Pair(arrayOf(3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(2, 3, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(1, 2)),
        Pair(arrayOf(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf(0, 1)),
        Pair(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), arrayOf())
    )

     */

    private lateinit var speedSeekBar: SeekBar
    private lateinit var speedValueTextView: TextView

    private var dataIndex = 0
    private val handler = Handler()
    private var delay: Long = 2000 // Delay in milliseconds
    private var isPaused = false
    private var speedMultiplier = 1 // Default speed multiplier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_controller)

        barChart = findViewById(R.id.barChart)
        speedSeekBar = findViewById(R.id.speedSeekBar)
        speedValueTextView = findViewById(R.id.speedValueTextView)

        val pauseButton: Button = findViewById(R.id.pauseButton)
        val speedUpButton: Button = findViewById(R.id.speedUpButton)
        val slowDownButton: Button = findViewById(R.id.slowDownButton)
        val restartButton: Button = findViewById(R.id.restartButton)

        pauseButton.setOnClickListener {
            isPaused = !isPaused
            if (!isPaused) {
                handler.postDelayed(animationRunnable, delay / speedMultiplier)
            }
        }

        speedUpButton.setOnClickListener {
            val newMultiplier = speedMultiplier * 2
            speedMultiplier = newMultiplier.coerceAtMost(speedSeekBar.max)
            updateSpeedUI()
            updateSeekBarProgress()
        }

        slowDownButton.setOnClickListener {
            if (speedMultiplier > 1) {
                speedMultiplier /= 2
                updateSpeedUI()
                updateSeekBarProgress()
            } else {
                // Set a lower limit for speedMultiplier to avoid going below 1
                speedMultiplier = 1
            }
        }

        restartButton.setOnClickListener {
            dataIndex = 0
            updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)
            isPaused = false
            handler.removeCallbacks(animationRunnable)
            handler.postDelayed(animationRunnable, delay / speedMultiplier)
        }

        val nextButton: Button = findViewById(R.id.nextButton)
        val prevButton: Button = findViewById(R.id.prevButton)

        nextButton.setOnClickListener {
            if (dataIndex < dataArray.size - 1) {
                // Move to the next set of values
                dataIndex++
                // Update the chart with the next set of values
                updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)
            }
        }

        prevButton.setOnClickListener {
            if (dataIndex > 0) {
                // Move to the previous set of values
                dataIndex--
                // Update the chart with the previous set of values
                updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)
            }
        }

        // Set up the SeekBar
        speedSeekBar.max = 128 // Adjust max value based on your needs
        speedSeekBar.progress = 0
        speedMultiplier = speedSeekBar.progress + 1
        updateSpeedUI()

        speedSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate speedMultiplier based on progress
                speedMultiplier = (progress + 1).coerceAtMost(speedSeekBar.max)
                updateSpeedUI()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this implementation
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Restart the animation with the updated speed
                if (!isPaused) {
                    handler.removeCallbacks(animationRunnable)
                    handler.postDelayed(animationRunnable, delay / speedMultiplier)
                }
            }
        })

        // Start the initial update
        updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)

        // Start the animation
        handler.postDelayed(animationRunnable, delay / speedMultiplier)
    }

    private val animationRunnable = object : Runnable {
        override fun run() {
            if (!isPaused) {
                // Move to the next set of values if available
                dataIndex++
                if (dataIndex < dataArray.size) {
                    // Update the chart with the next set of values
                    updateBarChart(dataArray[dataIndex].first, dataArray[dataIndex].second)

                    // Repeat the process with the updated speed
                    handler.postDelayed(this, delay / speedMultiplier)
                } else {
                    // Stop further updates when there's no more data
                    handler.removeCallbacks(this)
                }
            }
        }
    }

    private fun updateBarChart(values: Array<Int>, highlightIndices: Array<Int>) {
        getBarChartData(values)

        // Set color for all bars to the default color
        val defaultColor = ContextCompat.getColor(this, R.color.purple_200)
        val colors = MutableList(barEntriesList.size) { defaultColor }

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

    private fun updateSpeedUI() {
        speedValueTextView.text = "Speed: x$speedMultiplier"
    }

    private fun updateSeekBarProgress() {
        // Update the SeekBar's progress based on the speedMultiplier
        speedSeekBar.progress = (speedMultiplier - 1).toInt()
    }
}