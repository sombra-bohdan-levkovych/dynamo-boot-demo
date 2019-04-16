package com.testmonkeys.demo.service;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.mapper.UserMapper;
import com.testmonkeys.demo.repo.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private UserMapper userMapper; //TODO

    @Mock
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        final ArrayList<User> testList = new ArrayList<>();
        testList.add(new User());
        when(userRepository.findAll()).thenReturn(testList);
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void getOneTest() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(new User().setId(3L)));
        assertEquals(3L, (long) userService.getOne(3L).getId());
    }

    @Test
    public void createUserTest(){
        final UserDTO userDTO = new UserDTO()
                .setName("Ostap")
                .setPosition("Java developer")
                .setRank("Middle")
                .setEmail("badformed35@em@il.c435om");
        exceptionRule.expect(IllegalArgumentException.class);
        userService.createUser(userDTO);
    }

    @Test
    public void findAllBEMiddleDevs() {
        final List<User> testUsersList = Arrays.asList(
                new User()
                .setId(1L).setFirstname("User1")
                .setPosition("Middle Java developer")
                .setActivated(true),
                new User()
                .setId(2L).setFirstname("User2")
                .setPosition("Middle Java developer")
                .setActivated(false),
                new User()
                .setId(3L).setFirstname("User3")
                .setActivated(false),
                new User()
                .setId(4L)
                .setPosition("Junior QA Manual")
                .setActivated(false),
                new User()
                .setId(5L).setFirstname("User5")
                .setPosition("Middle FE developer")
                .setActivated(true), null,
                new User()
                .setId(6L).setFirstname("User6")
                .setPosition("Middle Java developer")
                .setActivated(true));
        when(userRepository.findAll()).thenReturn(testUsersList);
        final List<UserDTO> users = userService.findAllActivatedBEMiddleDevs();
        assertNotNull(users);
        for (final UserDTO userDTO : users) {
            assertNotNull(userDTO.getId());
            assertNotNull(userDTO.getName());
            assertEquals(userDTO.getPosition(), "Java developer");
            assertEquals(userDTO.getRank(), "Middle");
        }
        assertEquals(2, users.size());

    }
}
