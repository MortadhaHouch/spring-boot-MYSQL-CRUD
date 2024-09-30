package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Foyer {
    @Id
    @Column(unique = true, nullable = false)
    Long id;
    @Column
    String name;
    @Column
    Long capacity;
    @JoinColumn
    @OneToOne
    University university;
    @OneToMany
    @Column
    @JoinColumn
    List<Block> blocks;
}
