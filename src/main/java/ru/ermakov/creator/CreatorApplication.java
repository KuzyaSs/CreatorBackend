package ru.ermakov.creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"application.properties"})
public class CreatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreatorApplication.class, args);
    }
}