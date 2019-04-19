package com.testmonkeys.demo.service;

import com.testmonkeys.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserServiceImpl repository;


    @Test
    public void getAllTest(){
        final List<User> all = repository.findAll();

        assertNotNull(all);
        assertEquals(182, all.size());

    }
}
