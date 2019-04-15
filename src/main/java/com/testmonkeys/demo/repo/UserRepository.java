package com.testmonkeys.demo.repo;

import com.testmonkeys.demo.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findById(String id);

    List<User> findAll();

}
