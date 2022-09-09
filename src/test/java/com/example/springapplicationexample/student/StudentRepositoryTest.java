package com.example.springapplicationexample.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldFindStudentByEmail() {
        Student first_student = new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
        Student second_student = new Student("John", "john.noob@gmail.com", LocalDate.of(1998, Month.MAY, 25));

        underTest.save(first_student);

        Optional<Student> correct_result = underTest.findStudentByEmail(first_student.getEmail());
        Optional<Student> incorrect_result = underTest.findStudentByEmail(second_student.getEmail());

        assert(correct_result.isPresent() && incorrect_result.isEmpty());
    }
}