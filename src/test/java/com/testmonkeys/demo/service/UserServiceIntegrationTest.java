package com.testmonkeys.demo.service;

import com.testmonkeys.demo.dto.AccountDTO;
import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.exception.BadRequestParametersException;
import com.testmonkeys.demo.repo.UserRepository;
import com.testmonkeys.demo.service.factory.AccountDTOTestFactory;
import com.testmonkeys.demo.utils.UserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Test
    public void getAllTest(){
        final List<User> all = userService.findAll();

        assertNotNull(all);
        assertTrue(all.size()>0);

    }

    @Test
    public void createFromAccountDTOTest(){
        List<User> all = userService.findAll();
        User hr = all.get(3);
        User mentor = all.get(4);
        User manager = all.get(5);
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        User user = userService.createFromAccountDTO(accountDTO, mentor, hr, manager);

        Assert.assertEquals(user.getMentor(), mentor);
        Assert.assertEquals(user.getMyHR(), hr);
        Assert.assertEquals(user.getMainManager(), manager);
        Assert.assertTrue(passwordEncoder.matches("sombra",user.getPassword()));
        Assert.assertEquals(user.getVacationDays(), Integer.valueOf(18));
        Assert.assertNotNull(user.getDepartment());
    }



    @Test
    public void userServiceValidateUserEmail(){
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        accountDTO.setEmail("bohdan.levkovych@sombrainc.com");

        Assertions.assertThrows(BadRequestParametersException.class, () ->  userService.validateUser(accountDTO));
    }

    @Test
    public void userServiceValidateUserSkype(){
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        accountDTO.setSkype("skype_96");

        Assertions.assertThrows(BadRequestParametersException.class, () ->  userService.validateUser(accountDTO));
    }

    @Test
    public void userServiceValidateUserPhoneOne(){
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        accountDTO.setPhoneOne("0111111207");

        Assertions.assertThrows(BadRequestParametersException.class, () ->  userService.validateUser(accountDTO));
    }

    @Test
    public void userServiceValidateUserPhoneTwo(){
        AccountDTO accountDTO = AccountDTOTestFactory.getRandomActiveUser();
        accountDTO.setPhoneTwo("0222222318");

        Assertions.assertThrows(BadRequestParametersException.class, () ->  userService.validateUser(accountDTO));
    }


    @Test
    public void createNewUserByRecruiterTestEmptyManagerEmail(){
        AccountDTO dto = AccountDTOTestFactory.getRandomActiveUser();
        dto.setBase64Avatar("base64");
        User mentor = userRepository.getOne(22L);
        User hr = userRepository.getOne(108L);
        User mainManager = userRepository.getOne(35L);


        Assertions.assertThrows(RuntimeException.class, () -> userService.createNewUserByRecruiter(dto));
    }

    @Test
    public void createNewUserByRecruiterTest(){
        AccountDTO dto = AccountDTOTestFactory.getRandomActiveUser();
        dto.setBase64Avatar("base64");
        User mentor = userRepository.getOne(22L);
        User hr = userRepository.getOne(108L);
        User mainManager = userRepository.getOne(35L);

        dto.setManagerEmail("testManager@gmail.com");
        Assertions.assertThrows(RuntimeException.class, () -> userService.createNewUserByRecruiter(dto));
    }

    @Test
    public void createNewUserByRecruiterTest1(){
        AccountDTO dto = AccountDTOTestFactory.getRandomActiveUser();
        dto.setBase64Avatar("base64");

        dto.setFirstname("Daniel");
        dto.setLastname("Kekos");
        dto.setBirthDate(LocalDate.now().minusYears(24));
        dto.setPhoneTwo("0634545391");
        dto.setPhoneTwo("0541236654");
        dto.setSkype("pepsiman2");
        dto.setPersonalEmail("daniel.kekos@gmail.com");
        dto.setEmail("danko.levko@sombrainc.com");
        dto.setOffice("LVIV");
        dto.setPosition("Junior Java Developer");
        dto.setMentorEmail("taras@sombrainc.com");
        dto.setManagerEmail("natalia.shulzhenko@sombrainc.com");
        dto.setHrEmail("anna.fedorus@sombrainc.com");
        dto.setDepartment("IT Department");
        UserDTO newUser = userService.createNewUserByRecruiter(dto);

        Assert.assertEquals(dto.getFirstname(), newUser.getFirstname());
        Assert.assertEquals(dto.getLastname(), newUser.getLastname());
        Assert.assertEquals(dto.getSkype(), newUser.getSkype());
        Assert.assertEquals(newUser.getPhoneOne(), UserValidator.formatPhoneNumber(dto.getPhoneOne()));
        Assert.assertEquals(newUser.getPhoneTwo(), UserValidator.formatPhoneNumber(dto.getPhoneTwo()));
        Assert.assertEquals(dto.getOffice(), dto.getOffice());
        Assert.assertEquals(dto.getEmail(), newUser.getEmail());
        Assert.assertEquals(dto.getPersonalEmail(), newUser.getPersonalEmail());
        Assert.assertEquals(dto.getPosition().substring( dto.getPosition().indexOf(" ")), newUser.getPosition());
        Assert.assertTrue(newUser.isActivated());
        Assert.assertNotNull(newUser.getId());

        Optional<User> justCreated = userRepository.findById(newUser.getId());

        Assert.assertNotNull(justCreated.get());
        Assert.assertEquals(justCreated.get().getFirstname(), newUser.getFirstname());
        Assert.assertEquals(justCreated.get().getLastname(), newUser.getLastname());
        Assert.assertEquals(justCreated.get().getSkype(), newUser.getSkype());
        Assert.assertEquals(newUser.getPhoneOne(),justCreated.get().getPhoneOne());
        Assert.assertEquals(newUser.getPhoneTwo(), justCreated.get().getPhoneTwo());
        Assert.assertEquals(justCreated.get().getOffice(), dto.getOffice());
        Assert.assertEquals(justCreated.get().getEmail(), newUser.getEmail());
        Assert.assertEquals(justCreated.get().getPersonalEmail(), newUser.getPersonalEmail());
        Assert.assertEquals(justCreated.get().getPosition(), dto.getPosition());

    }

    @Test
    public void createNewUserByRecruiterTestWithMissingManager(){
        AccountDTO dto = AccountDTOTestFactory.getRandomActiveUser();
        dto.setBase64Avatar("base64");


        User mentor = userRepository.getOne(22L);
        User hr = userRepository.getOne(108L);
        User mainManager = userRepository.getOne(35L);

        dto.setMentorEmail("taras@sombrainc.com");

        dto.setHrEmail("anna.fedorus@sombrainc.com");
        dto.setManagerEmail("testManager@gmail.com");
        Assertions.assertThrows(RuntimeException.class, () ->  userService.createNewUserByRecruiter(dto));
    }

}
