package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Foyer {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column
    String name;
    @Column
    Long capacity;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    University university;
    @OneToMany(cascade = CascadeType.ALL)
    @Column
    @JoinColumn
    List<Block> blocks = new ArrayList<>();
}
