package com.example.springapplicationexample.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentServiceTest {

    private StudentRepository studentRepositoryMock;

    StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepositoryMock = mock(StudentRepository.class);
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    void itShouldGetStudentsTest() {
        List<Student> dummyList = List.of(new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15)));
        Mockito.when(studentRepositoryMock.findAll()).thenReturn(dummyList);

        assertEquals(studentService.getStudents(), dummyList);
    }

    @Test
    void itShouldNotAddNewStudentTest() {
        Student dummyStudent = new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
        Mockito.when(studentRepositoryMock.findStudentByEmail(dummyStudent.getEmail())).thenReturn(Optional.of(dummyStudent));

        assertThrows(IllegalStateException.class, () -> studentService.addNewStudent(dummyStudent), "Email already taken");
    }

    @Test
    void itShouldNotDeleteStudentTest() {
        Student dummyStudent = new Student(1L, "Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
        Mockito.when(studentRepositoryMock.existsById(dummyStudent.getId())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(dummyStudent.getId()), "Student not found with ID: " + dummyStudent.getId());
    }

    @Test
    void itShouldNotUpdateStudentBecauseDoesNotExistTest() {
        Student dummyStudent = new Student(1L, "Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));

        // Testing if IllegalStateException is thrown when student to modify is not present in the database
        Mockito.when(studentRepositoryMock.findById(dummyStudent.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class,
                () -> studentService.updateStudent(dummyStudent.getId(), dummyStudent.getName(), dummyStudent.getEmail()),
                "Student with id " + dummyStudent.getId() + " does not exist");
    }

    /*
    @Test
    void itShouldNotUpdateStudentBecauseEmailIsAlreadyTakenTest() {
        Student dummyStudent = new Student(1L, "Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
        Student secondDummyStudent = new Student(2L, "John", "jack.noobie@gmail.com", LocalDate.of(1994, Month.JUNE, 14));

        // Testing if IllegalStateException is thrown when the email of the student to modify is already taken
        Mockito.when(studentRepositoryMock.findById(dummyStudent.getId())).thenReturn(Optional.of(dummyStudent));
        Mockito.when(studentRepositoryMock.findStudentByEmail(dummyStudent.getEmail())).thenReturn(Optional.of(secondDummyStudent));

        assertThrows(IllegalStateException.class,
                () -> studentService.updateStudent(1L, "Al", "jack.noobie@gmail.com"),
                "Email for student " + dummyStudent.getId() + " already taken");
    }
    */
}