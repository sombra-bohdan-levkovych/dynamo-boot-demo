package com.testmonkeys.demo.service;


import com.testmonkeys.demo.utils.UserValidator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserValidatorTest {

    @Test
    public void checkEmailEqualityTest(){

        String email1 = "test@gamil.com";
        String email2 = "test@gamil.com";

        assertThrows(RuntimeException.class, () -> UserValidator.checkEmailEquality(email1,email2));
    }

    @Test
    public void checkPhoneEqualityTest(){

        String email1 = "8080";
        String email2 = "8080";

        assertThrows(RuntimeException.class, () -> UserValidator.checkPhonesEquality(email1,email2));
    }

    @Test
    public void checkDatesBefore1900ValidityTest(){
        LocalDate birthday = LocalDate.of(1899, 1, 1);
        LocalDate startWorkDate = LocalDate.now();

        assertThrows(RuntimeException.class, () -> UserValidator.checkDatesValidity(birthday,startWorkDate));
    }

    @Test
    public void checkDateFutureValidityTest(){
        LocalDate birthday = LocalDate.now().plusDays(2);
        LocalDate startWorkDate = LocalDate.now();

        assertThrows(RuntimeException.class, () -> UserValidator.checkDatesValidity(birthday,startWorkDate));
    }

    @Test
    public void checkBithDateAfterStartValidityTest(){
        LocalDate birthday = LocalDate.now().minusDays(1);
        LocalDate startWorkDate = LocalDate.now().minusDays(4);

        assertThrows(RuntimeException.class, () -> UserValidator.checkDatesValidity(birthday,startWorkDate));
    }
}
