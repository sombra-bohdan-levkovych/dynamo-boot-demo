package com.testmonkeys.demo.service;


import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.mapper.UserMapper;
import com.testmonkeys.demo.service.factory.UserTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UserMapperTest {


    @Test
    public void userMapperToDTOTest(){
        User user = UserTestFactory.getRandomActiveUser();
        String position = "Junior QA Manual";
        user.setPosition(position);

        String expectedPosition = position.substring(position.indexOf(" "));
        UserDTO dto = UserMapper.toDTO(user);

        Assert.assertEquals(user.getId(), dto.getId());
        Assert.assertEquals(user.getFirstname(), dto.getFirstname());
        Assert.assertEquals(expectedPosition, dto.getPosition());
    }

    @Test
    public void fromDTOTest(){
        UserDTO dto = new UserDTO();
        dto.setFirstname("test");
        dto.setPosition("WordPress developer");
        dto.setId(1l);
        User user = UserMapper.fromDTO(dto);

        Assert.assertEquals(user.getId(), dto.getId());
        Assert.assertEquals(user.getFirstname(), dto.getFirstname());
        Assert.assertEquals(user.getPosition(), dto.getPosition());

    }
}
