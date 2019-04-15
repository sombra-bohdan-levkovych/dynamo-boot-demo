package com.testmonkeys.demo.service;

import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest(){
        final ArrayList<User> testList = new ArrayList<>();
        testList.add(new User());
        when(userRepository.findAll()).thenReturn(testList);
        assertEquals(1, userService.findAll().size());
    }
}
