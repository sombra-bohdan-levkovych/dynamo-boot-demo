//package com.testmonkeys.demo.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.util.StringUtils;
//
////@Configuration
////@EnableDynamoDBRepositories(basePackages = "com.testmonkeys.demo.repo.dynamo")
//public class DynamoDBConfig {
//
//    @Value("${amazon.dynamodb.endpoint}")
//    private String amazonDynamoDBEndpoint;
//
//    @Value("${amazon.aws.accesskey}")
//    private String amazonAWSAccessKey;
//
//    @Value("${amazon.aws.secretkey}")
//    private String amazonAWSSecretKey;
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        AmazonDynamoDB dynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
//
//        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
//            dynamoDB.setEndpoint(amazonDynamoDBEndpoint);
//        }
//
//        return dynamoDB;
//    }
//
//    @Bean
//    public AWSCredentials amazonAWSCredentials() {
//        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//    }
//}