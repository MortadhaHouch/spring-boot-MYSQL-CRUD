package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;
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
    Date dob;
    @Column(nullable = false)
    Long CIN;
    @ManyToMany
    @Column(nullable = false)
    List<Reservation> reservations;
}
