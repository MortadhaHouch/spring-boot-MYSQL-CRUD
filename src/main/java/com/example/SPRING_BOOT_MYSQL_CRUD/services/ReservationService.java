package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    };
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
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
    public List<Student> findStudentsByReservationId(Long id) {
        Optional<Reservation> foundReservation = reservationRepo.findById(id);
        if(foundReservation.isPresent()){
            return foundReservation.get().getStudents();
        }else{
            return List.of();
        }
    }
}
