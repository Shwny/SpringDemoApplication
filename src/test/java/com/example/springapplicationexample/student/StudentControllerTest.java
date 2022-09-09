package com.example.springapplicationexample.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    private StudentController studentController;
    private StudentService studentServiceMock;

    @BeforeEach
    void setUp() {
        studentServiceMock = Mockito.mock(StudentService.class);
        studentController = new StudentController(studentServiceMock);
    }

    @Test
    void shouldGetStudentsTest() {
        List<Student> dummyList = List.of(new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15)));
        Mockito.when(studentServiceMock.getStudents()).thenReturn(dummyList);

        assertEquals(dummyList, studentController.getStudents());
    }

}