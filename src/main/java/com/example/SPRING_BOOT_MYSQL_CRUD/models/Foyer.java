package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Foyer {
    @Id
    Long id;
    String name;
    Long capacity;
    @JoinColumn
    @OneToOne
    University university;
    @OneToMany
    @JoinColumn
    List<Block> blocks;
}
