package com.testmonkeys.demo.service;

import com.google.gson.Gson;
import com.testmonkeys.demo.dto.SombraUser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public List<SombraUser> getUserFromJson() throws IOException {
        Gson gson = new Gson();

        String json = StreamUtils.copyToString(
                new ClassPathResource("users.json").getInputStream(),
                Charset.defaultCharset());

        SombraUser[] arr = gson.fromJson(json, SombraUser[].class);

        return Arrays.asList(arr);
    }

}
