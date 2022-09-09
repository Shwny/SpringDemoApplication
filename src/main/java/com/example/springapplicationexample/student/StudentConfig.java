package com.example.springapplicationexample.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// A configuration class. This is used for the initial configuration of the Database when the application is started.
@Configuration
public class StudentConfig {

     @Bean
     CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Jack = new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
            Student John = new Student("John", "john.noob@gmail.com", LocalDate.of(1997, Month.MAY, 30));
            repository.saveAll(List.of(Jack, John));
        };
    }

}
