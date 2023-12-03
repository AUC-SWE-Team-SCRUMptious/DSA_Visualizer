package com.scrumptious.algorithmvisualizer

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

class EmailValidatorTest {
    private lateinit var emailValidator: EmailValidator
    private lateinit var registerActivity: RegisterActivity
    @Before
    fun setUp() {
        registerActivity = RegisterActivity()
        emailValidator = EmailValidator()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun invalidEmailFormat_returnsFalse() {
        val emailValidator = EmailValidator()
        Assertions.assertEquals(false, emailValidator.validate(registerActivity, ""))

    }

    @Test
    fun validEmailFormat_returnsTrue() {
        val emailValidator = EmailValidator()
        Assertions.assertEquals(true, emailValidator.validate(registerActivity, "something@gmail.com"))

    }
}