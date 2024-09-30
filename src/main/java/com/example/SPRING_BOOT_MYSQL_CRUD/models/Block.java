package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Block {
    @Id
    @Column(nullable = false)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Long capacity;
    @ManyToOne
    Foyer foyer;
    @OneToMany
    @JoinColumn(nullable = false)
    @Column(nullable = false)
    List<Room> rooms;
}
