package com.scrumptious.algorithmvisualizer

class EmailValidator (private var email: String) {
    private var valid: Boolean
    init {
        valid = validateEmail(email)
    }
    //Check if the email is of valid format
    private fun validateEmail(str: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
    public fun getValid(): Boolean{
        return valid
    }
}