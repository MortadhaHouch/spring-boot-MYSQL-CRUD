package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    public Optional<Reservation> findReservationById(UUID id) {
        return reservationRepo.findById(id);
    };
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }
    public String addReservation(Reservation reservation) {
        reservationRepo.save(reservation);
        return "Reservation added";
    }
    public Reservation save(Reservation reservation) {
        Optional<Reservation> foundReservation = reservationRepo.findById(reservation.getId());
        if (foundReservation.isPresent()) {
            foundReservation.get().setId(reservation.getId());
            foundReservation.get().setStudents(reservation.getStudents());
            foundReservation.get().setIsValid(reservation.getIsValid());
            foundReservation.get().setYear(reservation.getYear());
            return reservationRepo.save(foundReservation.get());
        }else{
            return null;
        }
    };
    public Optional<List<Student>> findStudentsByReservationId(UUID id) {
        Optional<Reservation> foundReservation = reservationRepo.findById(id);
        return Optional.ofNullable(foundReservation.get().getStudents());
    }
}
