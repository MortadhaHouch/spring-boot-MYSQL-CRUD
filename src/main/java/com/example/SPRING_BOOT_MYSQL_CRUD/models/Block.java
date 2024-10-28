package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Block {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Long capacity;

    @ManyToOne(cascade = CascadeType.MERGE) // Adjust cascade to avoid unnecessary insertions
    Foyer foyer;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Room> rooms = new ArrayList<>();
}

