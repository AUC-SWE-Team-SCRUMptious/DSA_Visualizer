package com.scrumptious.algorithmvisualizer

class EmailValidator (private var email: String) {
    var valid: Boolean
    init {
        valid = validateEmail(email)
    }
    private fun validateEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
}