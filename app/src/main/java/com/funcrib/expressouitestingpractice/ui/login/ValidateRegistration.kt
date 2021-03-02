package com.funcrib.expressouitestingpractice.ui.login

object ValidateRegistration {
    fun validateEmail(email: String): Boolean {
        val pattern = "\\w+@[a-zA-Z_]+\\.+[a-zA-Z]+".toRegex()
        return email.matches(pattern)
    }

    fun validateNumber(number: String): Boolean {
        return when {
            number.length < 11 -> {
                false
            }
            number.length == 14 -> {
                number.substring(0, 4) == "+234" && (number[4] == '7' || number[4] == '8' || number[4] == '9')
            }
            number.length == 13 -> {
                number.substring(0, 3) == "234" && (number[3] == '7' || number[3] == '8' || number[3] == '9')
            }
            number.length == 11 -> {
                number.first() == '0'  && (number[1] == '7' || number[1] == '8' || number[1] == '9')
            }
            else -> false
        }

    }
}