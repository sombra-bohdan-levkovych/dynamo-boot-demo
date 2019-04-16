package com.testmonkeys.demo.mapper;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        final String position = user.getPosition();
        final String rank = position.substring(0, position.indexOf(" "));
        return new UserDTO()
                .setId(user.getId())
                .setName(user.getFirstname())
                .setPosition(position.substring(position.indexOf(" ")))
                .setRank(rank);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getName())
                .setPosition(userDTO.getRank() + " " + userDTO.getPosition());
    }
}
