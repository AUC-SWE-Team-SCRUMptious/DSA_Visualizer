package com.scrumptious.algorithmvisualizer

class PasswordValidator (var pass: String){

    var lengthCheck: Boolean
    var uppercaseReq: Boolean
    var lowercaseReq: Boolean
    var numReq: Boolean
    var valid: Boolean
    init {
        lengthCheck = false
        uppercaseReq = false
        lowercaseReq = false
        numReq = false
        valid = false
        validatePassword(pass)
    }
    fun validatePassword(pass: String){

        lengthCheck = pass.length>=6 && pass.length <=20;
        if (lengthCheck)
        {
            for (item in pass.indices){
                if (pass[item] == pass[item].uppercaseChar() && !uppercaseReq)
                {
                    uppercaseReq = true;
                }
                if (pass[item] == pass[item].lowercaseChar() && !lowercaseReq)
                {
                    lowercaseReq = true;
                }
                if (pass[item].isDigit() && !lowercaseReq)
                {
                    numReq = true;
                }
            }
        }

        if (lengthCheck && uppercaseReq && lowercaseReq && numReq && valid)
            valid = true;
    }
}