package com.testmonkeys.demo.service.factory;

import com.testmonkeys.demo.dto.AccountDTO;

import java.time.LocalDate;
import java.util.Arrays;

public class AccountDTOTestFactory {

    private static Integer count = 1;

    public static AccountDTO getRandomActiveUser(){
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setFirstname("FirstName" + count.toString());
        accountDTO.setLastname("LastName" + count.toString());
        accountDTO.setEmail("email" + count.toString() + "@gmail.com");
        accountDTO.setActivated(true);
        accountDTO.setId(Long.valueOf(count));
        accountDTO.setPhoneOne("805045154" + count);
        accountDTO.setPhoneTwo("484548463" + count);
        accountDTO.setBirthDate(LocalDate.now().minusYears(25));
        accountDTO.setStartsWork(LocalDate.now().minusYears(2));
        accountDTO.setRoles(Arrays.asList("Junior Java Developer","Middle Java Developer"));
        count = count + 1;
        return accountDTO;

    }
}
