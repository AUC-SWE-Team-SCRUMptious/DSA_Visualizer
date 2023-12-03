package com.scrumptious.algorithmvisualizer

import androidx.appcompat.app.AppCompatActivity
import kotlin.Boolean as Boolean1

interface UserInfoValidator {
    fun validate(page: AppCompatActivity, info: String): Boolean1
}