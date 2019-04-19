package com.testmonkeys.demo.service;

import com.testmonkeys.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @InjectMocks
    private UserServiceImpl repository;


    @Test
    public void getAllTest(){
        List<User> all = repository.findAll();

        assertNotNull(all);

    }
}
