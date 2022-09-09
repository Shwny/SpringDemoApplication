package com.example.springapplicationexample.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This class is marked with the '@Repository' annotation, and represents an auto-configured way to map the application
// persistence technology (Database) to a Java object (interface). Another way to see it is an auto-configured database handler.
// This allows Spring to expose many basic operations for the specific Database but does not prevent the user
// from defining more specific/advanced queries.
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
