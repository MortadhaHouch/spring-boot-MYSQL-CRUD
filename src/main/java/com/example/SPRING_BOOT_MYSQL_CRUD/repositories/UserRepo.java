package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Student, UUID> {
    Optional<Student> findStudentByFirstName(String firstName);
    Optional<Student> findStudentByLastName(String lastName);
    Optional<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);
    Optional<Student> findStudentByDob(Date dob);
    Optional<Student> findById(UUID id);
}