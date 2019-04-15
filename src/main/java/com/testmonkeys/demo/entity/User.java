package com.testmonkeys.demo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDate;

@DynamoDBTable(tableName = "user")
public class User {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate birthDate;
    private Boolean activated;
    private String phoneOne;
    private String phoneTwo;
    private String skype;
    private String position;
    private Integer sombraMoney;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getFirstname() {
        return firstname;
    }

    @DynamoDBAttribute
    public String getLastname() {
        return lastname;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @DynamoDBAttribute
    public Boolean getActivated() {
        return activated;
    }

    @DynamoDBAttribute
    public String getPhoneOne() {
        return phoneOne;
    }

    @DynamoDBAttribute
    public String getPhoneTwo() {
        return phoneTwo;
    }

    @DynamoDBAttribute
    public String getSkype() {
        return skype;
    }

    @DynamoDBAttribute
    public String getPosition() {
        return position;
    }

    @DynamoDBAttribute
    public Integer getSombraMoney() {
        return sombraMoney;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSombraMoney(Integer sombraMoney) {
        this.sombraMoney = sombraMoney;
    }
}