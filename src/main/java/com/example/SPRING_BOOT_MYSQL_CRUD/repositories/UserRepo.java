package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByFirstName(String firstName);
    Optional<Student> findStudentByLastName(String lastName);
    Optional<Student> findStudentById(Long id);
    Optional<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);
    Optional<Student> findStudentByDob(Date dob);
}