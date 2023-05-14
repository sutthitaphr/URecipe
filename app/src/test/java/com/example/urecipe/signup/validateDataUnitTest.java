package com.example.urecipe.signup;

import static org.junit.Assert.*;

import org.junit.Test;

public class validateDataUnitTest {

    @Test
    public void validateData_SignUpWithEmailandPassword_ReturnsTrue(){
        assertTrue(SignUpActivity.validateData("name@email.com","password","password"));
    }
}