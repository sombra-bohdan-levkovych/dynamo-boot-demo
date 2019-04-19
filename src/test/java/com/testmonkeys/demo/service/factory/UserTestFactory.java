package com.testmonkeys.demo.service.factory;

import com.testmonkeys.demo.entity.User;

public class UserTestFactory {

    private static Integer count = 1;

    public static User getRandomActiveUser(){
        User user = new User();

        user.setFirstname("FirstName" + count.toString());
        user.setLastname("LastName" + count.toString());
        user.setEmail("email" + count.toString() + "@gmail.com");
        user.setActivated(true);
        user.setId(Long.valueOf(count));
        user.setPhoneOne("805045154" + count);
        user.setPhoneTwo("484548463" + count);

        count = count + 1;
        return user;

    }


}
