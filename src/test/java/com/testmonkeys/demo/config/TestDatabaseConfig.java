//package com.testmonkeys.demo.config;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import org.apache.commons.lang.CharEncoding;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.EncodedResource;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//
//import javax.sql.DataSource;
//import java.beans.PropertyVetoException;
//import java.sql.SQLException;
//
//@Configuration
//public class TestDatabaseConfig {
//
//    @Bean
//    @DependsOn("dockerContainerLifecycleManager")
//    public DataSource mysqlContainerDatasource() throws SQLException {
//        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//        } catch (PropertyVetoException e) {
//            throw new IllegalArgumentException("Error with driver class", e);
//        }
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3307/space_test?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=");
//        dataSource.setUser("root");
//        dataSource.setPassword("root");
//        dataSource.setTestConnectionOnCheckin(true);
//        dataSource.setTestConnectionOnCheckout(true);
//        dataSource.setPreferredTestQuery("SELECT 1");
//        ScriptUtils.executeSqlScript(dataSource.getConnection(), new EncodedResource(new ClassPathResource("sql/test_dump.sql"), CharEncoding.UTF_8));
//        return dataSource;
//    }
//}
