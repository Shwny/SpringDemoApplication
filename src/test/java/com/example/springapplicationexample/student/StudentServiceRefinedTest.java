package com.example.springapplicationexample.student;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Fix tests using verify on the RepositoryMockMethod
@ExtendWith(MockitoExtension.class)
class StudentServiceRefinedTest {

    @Mock
    StudentRepository studentRepositoryMock;

    @InjectMocks
    StudentService studentService;

    List<Student> dummyList;
    Student dummyStudent;
    Student secondDummyStudent;

    // The SetUp method should not be used (or at least, not only) to initialize the dependency injection manually, but
    // also all the data that will be used by the test.

    // THIS IS FLAWED, SHOULD NOT BE RUNNING BEFORE EACH OF THE TEST.
    @BeforeEach
    void setUp() {
        this.dummyList = List.of(new Student("Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15)));
        this.dummyStudent = new Student(1L, "Jack", "jack.noob@gmail.com", LocalDate.of(1997, Month.JULY, 15));
        this.secondDummyStudent = new Student(2L, "John", "jack.noobie@gmail.com", LocalDate.of(1994, Month.JUNE, 14));
    }

    @Test
    void itShouldGetStudentsTest() {
        when(studentRepositoryMock.findAll()).thenReturn(dummyList);

        studentService.getStudents();
        verify(studentRepositoryMock).findAll();
        assertEquals(studentService.getStudents(), dummyList);
    }

    @Test
    void itShouldNotAddNewStudentTest() {
        when(studentRepositoryMock.findStudentByEmail(dummyStudent.getEmail())).thenReturn(Optional.of(dummyStudent));

        assertThrows(IllegalStateException.class, () -> studentService.addNewStudent(dummyStudent), "Email already taken");
    }

    @Test
    void itShouldCallFindStudentByEmailAndSave() {
        when(studentRepositoryMock.findStudentByEmail(dummyStudent.getEmail())).thenReturn(Optional.empty());
        studentService.addNewStudent(dummyStudent);

        verify(studentRepositoryMock).findStudentByEmail(dummyStudent.getEmail());
        verify(studentRepositoryMock).save(dummyStudent);
    }

    @Test
    void itShouldNotDeleteStudentTest() {
        when(studentRepositoryMock.existsById(dummyStudent.getId())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(dummyStudent.getId()), "Student not found with ID: " + dummyStudent.getId());
    }

    @Test
    void itShouldCallExistsByIdAndDeleteById() {
        when(studentRepositoryMock.existsById(dummyStudent.getId())).thenReturn(true);
        studentService.deleteStudent(dummyStudent.getId());

        verify(studentRepositoryMock).existsById(dummyStudent.getId());
        verify(studentRepositoryMock).deleteById(dummyStudent.getId());
    }

    @Test
    void itShouldNotUpdateStudentBecauseDoesNotExistTest() {
        // Testing if IllegalStateException is thrown when student to modify is not present in the database
        when(studentRepositoryMock.findById(dummyStudent.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class,
                () -> studentService.updateStudent(dummyStudent.getId(), dummyStudent.getName(), dummyStudent.getEmail()),
                "Student with id " + dummyStudent.getId() + " does not exist");
    }

    @Test
    void itShouldCallFindByIdAndFindStudentByEmail() {
        Long id = 1L;
        String name ="Al";
        String email = "al.noob@gmail.com";

        when(studentRepositoryMock.findById(dummyStudent.getId())).thenReturn(Optional.of(dummyStudent));
        when(studentRepositoryMock.findStudentByEmail(email)).thenReturn(Optional.empty());

        studentService.updateStudent(id, name, email);
        verify(studentRepositoryMock).findById(dummyStudent.getId());
        verify(studentRepositoryMock).findStudentByEmail(email);
    }

    /*
    @Test
    void itShouldNotUpdateStudentBecauseEmailIsAlreadyTakenTest() {

        // Testing if IllegalStateException is thrown when the email of the student to modify is already taken
        when(studentRepositoryMock.findById(dummyStudent.getId())).thenReturn(Optional.of(dummyStudent));
        when(studentRepositoryMock.findStudentByEmail(dummyStudent.getEmail())).thenReturn(Optional.of(secondDummyStudent));

        assertThrows(IllegalStateException.class,
                () -> studentService.updateStudent(1L, "Al", "jack.noobie@gmail.com"),
                "Email for student " + dummyStudent.getId() + " already taken");
    }
    */
}