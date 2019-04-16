package com.testmonkeys.demo.repo;

import com.testmonkeys.demo.entity.User;
import com.testmonkeys.demo.enums.PositionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(@Param(value = "email") String email);

    Optional<User> findOneByEmail(String email);


    @Query(value = "SELECT u FROM User u WHERE u.email = :email OR u.personalEmail = :email")
    List<User> findAllByEmailOrPersonalEmail(@Param("email") String email);

    User findFirstById(Long id);

    @Query(value = "SELECT u FROM User u WHERE u.phoneOne = :phone OR u.phoneTwo = :phone")
    List<User> findAllByPhoneOneOrPhoneTwo(@Param("phone") String phone);

    List<User> findAllBySkype(String skype);

    User findOneByPositionAndOffice(PositionEnum position, String Office);

}
