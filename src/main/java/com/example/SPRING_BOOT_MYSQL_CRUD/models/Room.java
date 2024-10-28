package com.example.SPRING_BOOT_MYSQL_CRUD.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,unique = true)
    UUID id;
    @Column(nullable = false)
    Long roomNumber;
    @Column(nullable = false)
    RoomType typeR;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id")
    @JsonIgnore
    Block block;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    List<Reservation> reservations = new ArrayList<>();
}
