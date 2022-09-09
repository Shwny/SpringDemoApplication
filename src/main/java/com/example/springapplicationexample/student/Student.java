package com.example.springapplicationexample.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

// This class represents an internal model of the data handled by the application. It is Marked with the '@Entity'
// annotation to be caught by the DI mechanism, and the '@Table' annotation is used by the JPA module to map a Table
// over this class data fields.
@Entity
@Table
public class Student {

    // Configuration for the mapping of the object (Entity) to the specific database that stores all of our data.
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id; // Unique ID
    private String name; // Name
    private String email; // Email address
    private LocalDate dob; // Date of birth

    // The transient annotation lets the framework know that the data field must NOT be mapped to a column
    // in the database table representation of the objects (in this case, the `age` data field will be ignored).
    @Transient
    private Integer age; // Age

    public Student() {} // No params constructor

    public Student(Long id, String name, String email, LocalDate dob) { // All params constructor
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) { // No id param constructor
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    // Getters and Setters for the class. These will also be used to interact with the database objects.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
