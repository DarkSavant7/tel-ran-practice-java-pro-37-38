package de.telran.marketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class MarketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketAppApplication.class, args);
    }

}
