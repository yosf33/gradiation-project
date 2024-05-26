package com.example.gradiationproject.data.rules

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ValidatorTest {

    @Test
    fun `validateFirstName - valid`() {
        val result = Validator.validateFirstName("John")
        assertTrue(result.status)
    }

    @Test
    fun `validateFirstName - invalid empty`() {
        val result = Validator.validateFirstName("")
        assertFalse(result.status)
    }

    @Test
    fun `validateFirstName - invalid short length`() {
        val result = Validator.validateFirstName("J")
        assertFalse(result.status)
    }

    @Test
    fun `validateLastName - valid`() {
        val result = Validator.validateLastName("Doe")
        assertTrue(result.status)
    }

    @Test
    fun `validateLastName - invalid empty`() {
        val result = Validator.validateLastName("")
        assertFalse(result.status)
    }

    @Test
    fun `validateLastName - invalid short length`() {
        val result = Validator.validateLastName("D")
        assertFalse(result.status)
    }

    @Test
    fun `validateEmail - valid`() {
        val result = Validator.validateEmail("john@example.com")
        assertTrue(result.status)
    }

    @Test
    fun `validateEmail - invalid empty`() {
        val result = Validator.validateEmail("")
        assertFalse(result.status)
    }

    @Test
    fun `validatePassword - valid`() {
        val result = Validator.validatePassword("password123")
        assertTrue(result.status)
    }

    @Test
    fun `validatePassword - invalid empty`() {
        val result = Validator.validatePassword("")
        assertFalse(result.status)
    }

    @Test
    fun `validatePassword - invalid short length`() {
        val result = Validator.validatePassword("123")
        assertFalse(result.status)
    }

}