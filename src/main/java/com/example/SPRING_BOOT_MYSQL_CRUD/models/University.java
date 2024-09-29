package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class University {
    @Id
    @Column(nullable = false,unique = true)
    Long id;
    @Column(nullable = false)
    String name;
    @JoinColumn(nullable = false)
    @OneToOne
    Foyer foyer;
}
