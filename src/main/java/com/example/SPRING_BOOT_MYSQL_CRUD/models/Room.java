package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @Column(nullable = false, unique = true)
    Long id;
    @Column(nullable = false)
    Long roomNumber;
    @Column(nullable = false)
    RoomType typeR;
    @OneToOne
    @JoinColumn(nullable = false)
    Block block;
    @OneToMany
    List<Reservation> reservations;
}
