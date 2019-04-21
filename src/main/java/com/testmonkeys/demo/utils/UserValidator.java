package com.testmonkeys.demo.utils;

import com.testmonkeys.demo.dto.AccountDTO;
import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.enums.Office;
import com.testmonkeys.demo.enums.PositionEnum;
import com.testmonkeys.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserValidator {


    @Autowired
    private UserRepository userRepository;

    public static void checkAndValidateOffice(User user, AccountDTO accountDTO){
        if (Office.isOfficeExist(accountDTO.getOffice())) {
            user.setOffice(accountDTO.getOffice().toUpperCase());
        }
    }

    public boolean checkEmailUser(String email, Long userId) {
        List<User> existingUsers = userRepository.findAllByEmailOrPersonalEmail(email);
        boolean isEmailValid = existingUsers.isEmpty();
        if (userId != null) {
            User user = userRepository.findFirstById(userId);
            if (existingUsers.contains(user)) {
                isEmailValid = true;
            }
        }
        return isEmailValid;
    }


    public boolean checkPhoneUser(String phone, Long userId) {
        if (phone == null || phone.isEmpty()) {
            return true;
        }
        List<User> existingUsers = userRepository.findAllByPhoneOneOrPhoneTwo(phone);
        boolean isPhoneValid = existingUsers.isEmpty();
        if (userId != null) {
            User user = userRepository.findFirstById(userId);
            if (existingUsers.contains(user)) {
                isPhoneValid = true;
            }
        }
        return isPhoneValid;
    }


    public boolean checkSkypeUser(String skype, Long userId) {
        List<User> existingUsers = userRepository.findAllBySkype(skype);
        boolean isSkypeValid = existingUsers.isEmpty();
        if (userId != null) {
            User user = userRepository.findFirstById(userId);
            if (existingUsers.contains(user)) {
                isSkypeValid = true;
            }
        }
        return isSkypeValid;
    }


    public boolean checkUniquenessOfHeadsOfOfficeAdministrations(String email, String position, String office) {
        if (office == null) {
            throw new RuntimeException("Office can`t be null");
        }
        final Optional<User> user = userRepository.findOneByEmail(email);
        if (user.isPresent()
                && user.get().getOffice().equals(office)
                && (user.get().getPosition().equals(PositionEnum.HEAD_OF_OFFICE_ADMIN.getValue()) || user.get().getPosition().equals(PositionEnum.HEAD_OF_SYS_ADMIN.getValue()))) {
            return true;
        }
        PositionEnum positionEnum = PositionEnum.findByPosition(position);
        if ((positionEnum.equals(PositionEnum.HEAD_OF_SYS_ADMIN) || positionEnum.equals(PositionEnum.HEAD_OF_OFFICE_ADMIN))) {
            return false;
        }
        return true;
    }


    public static void checkDatesValidity(LocalDate birthDay, LocalDate startsWork) {
        LocalDate minimumBirthDate = LocalDate.of(1900, 1, 1);
        if (birthDay.isBefore(minimumBirthDate)) {
            throw new RuntimeException("Can't set birthday before " + minimumBirthDate);
        }
        if (birthDay.isAfter(LocalDate.now())) {
            throw new RuntimeException("Can't set future birthday");
        }
        if (birthDay.isAfter(startsWork)) {
            throw new RuntimeException("Can't start work before birthday");
        }
    }

    public static void checkEmailEquality(String one, String two) {
        if (one.equals(two)) {
            throw new RuntimeException("Emails can't be the same");
        }
    }

    public static void checkPhonesEquality(String one, String two) {
        if (one.equals(two)) {
            throw new RuntimeException("Phones can't be the same");
        }
    }

    public static void checkAvatarForUser(String base64Avatar) {
        if (Objects.isNull(base64Avatar)) {
            throw new RuntimeException("Set image for the(a) user");
        }
    }

    public static void checkRoles(List<String> roles) {
        if (roles.size() == 1 ) {
            throw new RuntimeException("Role can't be only additional.");
        }
    }

    public static String formatPhoneNumber(String phoneNumber) {
        return new StringBuilder(phoneNumber).insert(3, " ").insert(7, " ").insert(10, " ").toString();
    }


}
