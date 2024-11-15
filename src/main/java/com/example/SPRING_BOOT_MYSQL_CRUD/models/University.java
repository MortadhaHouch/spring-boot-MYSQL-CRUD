package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    UUID id;
    @Column(nullable = false,unique = true)
    String name;
    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    Foyer foyer;
    @Column(nullable = false)
    String address;
}
