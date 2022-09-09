package com.example.springapplicationexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The main entry point for the application. This class is responsible for the preparation of all the internal mechanisms
// that the framework performs automatically.
@SpringBootApplication
public class SpringApplicationExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationExampleApplication.class, args);
    }

}
