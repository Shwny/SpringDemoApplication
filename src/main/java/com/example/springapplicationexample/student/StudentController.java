package com.example.springapplicationexample.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// A REST controller. This class serves as an entry point for the application (possibly one of many)
@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    // A reference to the StudentService class, which allows to perform specific business operations onto the received data.
    private final StudentService studentService;

    // The `@Autowired` annotation is used to let the framework know that the construction of this object is delegated to the
    // DI module, that will automatically create the object for us with its dependencies satisfied.
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // The '@GetMapping' annotation marks the methods that are invoked when a GET request is sent to the specified endpoint
    @GetMapping
    public List<Student> getStudents() { return studentService.getStudents(); }

    // The '@PostMapping' annotation marks the methods that are invoked when a POST request is sent to the specified endpoint
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    // The '@DeleteMapping' annotation marks the methods that are invoked when a DELETE request is sent to the specified endpoint
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    // The '@PutMapping' annotation marks the methods that are invoked when a PUT request is sent to the specified endpoint
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }

}
