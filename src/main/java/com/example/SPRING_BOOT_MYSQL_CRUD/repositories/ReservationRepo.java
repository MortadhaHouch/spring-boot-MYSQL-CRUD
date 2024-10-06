package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepo extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findReservationById(UUID reservationId);
    Optional<Reservation> findReservationByYear(Date year);
    Optional<Reservation> findReservationByIsValid(Boolean valid);
    Optional<Reservation> findById(UUID id);
}