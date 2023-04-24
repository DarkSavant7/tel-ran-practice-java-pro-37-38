package de.telran.marketapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

//@SpringBootTest
class MarketAppApplicationTests {

//    @Container
//    private static MySQLContainer container = new MySQLContainer("mysql")
//            .withDatabaseName("market")
//            .withUsername("admin")
//            .withPassword("admin");
//
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//    }
//
//    @Test
//    void contextLoads() {
//    }

}