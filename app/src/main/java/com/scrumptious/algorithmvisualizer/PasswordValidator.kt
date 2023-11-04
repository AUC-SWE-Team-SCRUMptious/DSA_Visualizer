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
    //Check if the password matches SRS 1.7.2 format
    private fun validatePassword(pass: String){

        lengthCheck = pass.length in 6..20;
        if (lengthCheck)
        {
            //go over every character in a password
            for (item in pass.indices){
                // check if it is a digit and mark the numeric requirement as true if so
                if (pass[item].isDigit())
                {
                    numReq = true;
                }
                else
                {
                    //check if the character is uppercase and mark the uppercase requirement if the character is uppercase and the requirement hasn't been met yet
                    if (pass[item] == pass[item].uppercaseChar() && !uppercaseReq)
                    {
                        uppercaseReq = true;
                    }
                    //check if the character is lowercase and mark the lowercase requirement if the character is lowercase and the requirement hasn't been met yet
                    if (pass[item] == pass[item].lowercaseChar() && !lowercaseReq)
                    {
                        lowercaseReq = true;
                    }
                }
            }
        }
        //mark total validity as true if the prior conditions were met
        if (lengthCheck && uppercaseReq && lowercaseReq && numReq)
            valid = true;
    }
}