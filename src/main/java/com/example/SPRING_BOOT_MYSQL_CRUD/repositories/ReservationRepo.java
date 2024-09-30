package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationById(Long reservationId);
    Optional<Reservation> findReservationByYear(Date year);
    Optional<Reservation> findReservationByIsValid(Boolean valid);
    Optional<Reservation> findReservationByStudents(Long studentId);
}