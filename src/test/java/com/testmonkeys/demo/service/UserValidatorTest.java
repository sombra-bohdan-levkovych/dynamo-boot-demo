package com.testmonkeys.demo.service;


import com.testmonkeys.demo.dto.AccountDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.repo.UserRepository;
import com.testmonkeys.demo.service.factory.AccountDTOTestFactory;
import com.testmonkeys.demo.service.factory.UserTestFactory;
import com.testmonkeys.demo.utils.UserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.testmonkeys.demo.utils.UserValidator.formatPhoneNumber;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    public void checkEmailUserTest(){

        User randomUser1 = UserTestFactory.getRandomActiveUser();
        User randomUser2 = UserTestFactory.getRandomActiveUser();

        List<User> users = new ArrayList<>();
        users.add(randomUser1);
        users.add(randomUser2);
        when(userRepository.findAllByEmailOrPersonalEmail(randomUser1.getEmail()))
                .thenReturn(users);

        when(userRepository.findFirstById(randomUser1.getId())).thenReturn(randomUser1);


        Assert.assertTrue(userValidator.checkEmailUser(randomUser1.getEmail(), randomUser1.getId()));
        users.remove(randomUser1);
        Assert.assertFalse(userValidator.checkEmailUser(randomUser1.getEmail(), randomUser1.getId()));
    }

    @Test
    public void checkEmailEqualityTest(){
        String email1 = "test@gamil.com";
        String email2 = "test@gamil.com";

        assertThrows(RuntimeException.class, () -> UserValidator.checkEmailEquality(email1,email2));
    }
    @Test
    public void checkEmailNonEqualityTest(){
        String email3 = "test@gamil1.com";
        String email4 = "test@gamil.com";

        UserValidator.checkEmailEquality(email3,email4);
    }

    @Test
    public void checkPhoneEqualityTest(){

        String email1 = "8080";
        String email2 = "8080";

        assertThrows(RuntimeException.class, () -> UserValidator.checkPhonesEquality(email1,email2));
    }

    @Test
    public void checkPhoneNonEqualityTest(){

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
    public void checkBirthDateAfterStartValidityTest(){
        LocalDate birthday = LocalDate.now().minusDays(1);
        LocalDate startWorkDate = LocalDate.now().minusDays(4);

        assertThrows(RuntimeException.class, () -> UserValidator.checkDatesValidity(birthday,startWorkDate));
    }

    @Test
    public void checkDateValidityTest(){
        LocalDate birthday = LocalDate.now().minusDays(800);
        LocalDate startWorkDate = LocalDate.now().minusDays(25);

        UserValidator.checkDatesValidity(birthday,startWorkDate);
    }

    @Test
    public void checkPhoneUserTest(){

        User randomUser1 = UserTestFactory.getRandomActiveUser();
        User randomUser2 = UserTestFactory.getRandomActiveUser();
        User randomUser3 = UserTestFactory.getRandomActiveUser();

        List<User> users = new ArrayList<>();
        users.add(randomUser1);
        users.add(randomUser2);
        users.add(randomUser3);

        when(userRepository.findAllByPhoneOneOrPhoneTwo(randomUser1.getPhoneOne()))
                .thenReturn(users);

        when(userRepository.findFirstById(randomUser1.getId())).thenReturn(randomUser1);


        Assert.assertTrue(userValidator.checkPhoneUser(randomUser1.getPhoneOne(), randomUser1.getId()));
        users.remove(randomUser1);
        Assert.assertFalse(userValidator.checkPhoneUser(randomUser1.getPhoneOne(), randomUser1.getId()));
    }

    @Test
    public void checkSkypeUserTest(){

        User randomUser1 = UserTestFactory.getRandomActiveUser();
        User randomUser2 = UserTestFactory.getRandomActiveUser();
        User randomUser3 = UserTestFactory.getRandomActiveUser();

        List<User> users = new ArrayList<>();
        users.add(randomUser1);
        users.add(randomUser2);
        users.add(randomUser3);

        when(userRepository.findAllBySkype(randomUser1.getSkype()))
                .thenReturn(users);

        when(userRepository.findFirstById(randomUser1.getId())).thenReturn(randomUser1);

        Assert.assertTrue(userValidator.checkSkypeUser(randomUser1.getSkype(), randomUser1.getId()));
        users.remove(randomUser1);
        Assert.assertFalse(userValidator.checkSkypeUser(randomUser1.getSkype(), randomUser1.getId()));
    }

    @Test
    public void checkValidRolesTest(){
        assertThrows(RuntimeException.class, () ->   UserValidator.checkRoles(Collections.singletonList("role")));;
    }

    @Test
    public void checkEmptyListRolesTest(){
        UserValidator.checkRoles(Collections.emptyList());
    }

    @Test
    public void checkAvatarNullForUserTest(){
        String avatar = null;
        assertThrows(RuntimeException.class, () -> UserValidator.checkAvatarForUser(avatar));
    }

    @Test
    public void checkAvatarForUserTest(){
        String avatar = "avatar";
        UserValidator.checkAvatarForUser(avatar);
    }

    @Test
    public void validateUserTest(){
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        User user = AccountDTO.createUser(accountDTO);

        Assert.assertEquals(accountDTO.getFirstname(),user.getFirstname());
        Assert.assertEquals(accountDTO.getLastname(),user.getLastname());
        Assert.assertEquals(accountDTO.getEmail(),user.getEmail());
        Assert.assertEquals(formatPhoneNumber(accountDTO.getPhoneOne()),user.getPhoneOne());
        Assert.assertEquals(formatPhoneNumber(accountDTO.getPhoneTwo()),user.getPhoneTwo());
        Assert.assertEquals(accountDTO.getSkype(),user.getSkype());
        Assert.assertTrue(user.isActivated());
    }


    @Test
    public void checkAndValidateOfficeTest(){
        String office = "IF";
        AccountDTO accountDTO = new AccountDTO().setOffice(office);
        User user = new User().setOffice(office);
        UserValidator.checkAndValidateOffice(user, accountDTO);
        Assert.assertEquals(office, user.getOffice());
    }

    @Test
    public void checkAndValidateNotExistingOfficeTest(){
        String office = "DE";
        AccountDTO accountDTO = new AccountDTO().setOffice(office);
        User user = new User();
        UserValidator.checkAndValidateOffice(user, accountDTO);
        Assert.assertNull(user.getOffice());
    }

    @Test
    public void formatPhoneNumberTest(){
        String expected = "805 045 15 41";
        String beforeFormat = "8050451541";
        String formatted =  formatPhoneNumber(beforeFormat);
        Assert.assertEquals(expected,formatted);
    }

    @Test
    public void formatPhoneNumberTestNotValid(){
        String expected = "8050451541";
        String beforeFormat = "8050451541";
        String formatted =  formatPhoneNumber(beforeFormat);
        Assert.assertNotEquals(expected,formatted);
    }

    @Test
    public void checkUniquenessOfHeadsOfOfficeAdministrationsTest(){
        String email = "bohdan.levkovych@sombrainc.com";
        String position = "position";
        String office = null;
        assertThrows(RuntimeException.class, () -> userValidator.checkUniquenessOfHeadsOfOfficeAdministrations(email, position, office));
    }

}
