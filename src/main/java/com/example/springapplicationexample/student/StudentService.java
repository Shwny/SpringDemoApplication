package com.example.springapplicationexample.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This class is marked with the '@Service' annotation, and serves as a handler for the internal business logics of the
// application. Is a dependency for the API Layer and is dependant from the Repository/Persistence Layer.
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);

    }

    // Using this annotation allows to interact with database entity by modifying the corresponding Java object
    // So to modify each field (after sanitization) all we need to do is call the correct setter for the specific parameter
    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email!= null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("Email for student " + id + " already taken");
            }
            student.setEmail(email);
        }
    }
}

