package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @Column(nullable = false,unique = true)
    Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String school;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    Date dob;
    @Column(nullable = false)
    Long CIN;
    @ManyToMany
    @Column(nullable = false)
    List<Reservation> reservations;
}
