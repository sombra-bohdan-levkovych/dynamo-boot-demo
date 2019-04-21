package com.testmonkeys.demo.mapper;

import com.testmonkeys.demo.dto.UserDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.enums.PositionEnum;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        final String position = user.getPosition();

        return new UserDTO()
                .setId(user.getId())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setPersonalEmail(user.getPersonalEmail())
                .setPhoneOne(user.getPhoneOne())
                .setPhoneTwo(user.getPhoneTwo())
                .setSkype(user.getSkype())
                .setOffice(user.getOffice())
                .setActivated(user.isActivated())
                .setPosition(position.substring(position.indexOf(" ")));
    }

    public static User fromDTO(UserDTO userDTO) {
        return new User().setId(userDTO.getId())
                .setFirstname(userDTO.getFirstname())
                .setPosition(PositionEnum.findByPosition(userDTO.getPosition()).getValue());
    }
}
