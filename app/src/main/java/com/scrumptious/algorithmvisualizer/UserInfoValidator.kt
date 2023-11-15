package com.scrumptious.algorithmvisualizer

import androidx.appcompat.app.AppCompatActivity

interface UserInfoValidator {
    fun validate(page: AppCompatActivity, pwd: String): Boolean
}