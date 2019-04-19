package com.testmonkeys.demo.mapper;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.enums.PositionEnum;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        final String position = user.getPosition();
        final String rank = position.substring(0, position.indexOf(" "));
        return new UserDTO()
                .setId(user.getId())
                .setFirstname(user.getFirstname())
                .setPosition(position.substring(position.indexOf(" ")));
    }

    public User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getFirstname())
                .setPosition(PositionEnum.findByPosition(userDTO.getPosition()).getValue());
    }
}
