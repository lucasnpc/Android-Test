package com.example.androidtest

import com.example.androidtest.data.LoginDataSource
import org.junit.Assert.*
import org.junit.Test

class LoginUnitTest {
    @Test
    fun loginIsResponsive(){
        assertEquals(null, LoginDataSource().login("dasdsa", "dsaa1"))
    }
}