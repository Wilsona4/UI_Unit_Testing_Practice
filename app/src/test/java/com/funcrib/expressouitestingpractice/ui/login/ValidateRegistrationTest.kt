package com.funcrib.expressouitestingpractice.ui.login


import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateRegistrationTest {
//    Expected

    private val email: String = "wilson@gmail.com"
    private val numberFalse: String = "0813553775"
    private val numberTrue: String = "+2348492015369"

    @Test
    fun validateMailTrue() {
        val result = ValidateRegistration.validateEmail(email)
        assertTrue(result)
    }

    @Test
    fun validatePhoneTrue() {
        val result = ValidateRegistration.validateNumber(numberTrue)
        assertTrue(result)
    }

    @Test
    fun validatePhoneFalse(){
        val result = ValidateRegistration.validateNumber(numberFalse)
        assertFalse(result)
    }

}