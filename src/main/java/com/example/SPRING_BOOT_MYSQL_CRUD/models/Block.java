package com.example.SPRING_BOOT_MYSQL_CRUD.models;

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
    @ManyToOne(cascade = CascadeType.ALL)
    Foyer foyer;
    @OneToMany
    @JoinColumn(nullable = false)
    @Column(nullable = false)
    List<Room> rooms = new ArrayList<>();
}
