package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.ReservationRepo;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

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
    public String deleteReservation(UUID id) {
        Optional<Reservation> foundReservation = reservationRepo.findById(id);
        if (foundReservation.isPresent()) {
            reservationRepo.delete(foundReservation.get());
            return  "reservation deleted";
        }else{
            return "reservation does not exist";
        }
    }
    @Transactional
    public String toggleAddReservationStudent(UUID reservationId, UUID studentId) {
        Optional<Reservation> foundReservation = reservationRepo.findById(reservationId);
        if (foundReservation.isPresent()) {
            Optional<Student> foundStudent = userService.getUserById(studentId);
            if (foundStudent.isPresent()) {
                Reservation reservation = foundReservation.get();
                Student student = foundStudent.get();
                if (reservation.getStudents().contains(student)) {
                    reservation.getStudents().remove(student);
                    student.getReservations().remove(reservation);
                    reservationRepo.save(reservation);
                    userRepo.save(student);
                    return "reservation cancelled";
                } else {
                    reservation.getStudents().add(student);
                    student.getReservations().add(reservation);
                    reservationRepo.save(reservation);
                    userRepo.save(student);
                    return "reservation added";
                }
            } else {
                return "student does not exist";
            }
        } else {
            return "reservation does not exist";
        }
    }
}
