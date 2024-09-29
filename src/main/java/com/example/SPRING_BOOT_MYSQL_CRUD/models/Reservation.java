package com.example.SPRING_BOOT_MYSQL_CRUD.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;
@Entity
@Data
public class Reservation {
    @Id
    @Column(nullable = false)
    Long id;
    @Column(nullable = false)
    Date year;
    @Column(nullable = false)
    Boolean isValid;
    @ManyToMany
    @Column(nullable = false)
    List<Student> students;
}
