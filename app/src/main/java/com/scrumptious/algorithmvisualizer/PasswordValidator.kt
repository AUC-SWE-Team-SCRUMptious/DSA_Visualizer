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
                if (!numReq && (pass[item] == '0' || pass[item] == '1' || pass[item] == '2' || pass[item] == '3' || pass[item] == '4' || pass[item] == '5' || pass[item] == '6' || pass[item] == '7' || pass[item] == '8' || pass[item] == '9') )
                {
                    numReq = true;
                }
            }
        }

        if (lengthCheck && uppercaseReq && lowercaseReq && numReq)
            valid = true;
    }
}