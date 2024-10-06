package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.ReservationService;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService =  new ReservationService();
    @Autowired
    UserService userService =  new UserService();
    @GetMapping("/get")
    public List<Reservation> getReservations() {
        return reservationService.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<Reservation> getReservationById(@PathVariable UUID id) {
        return reservationService.findReservationById(id);
    };
    @GetMapping("/get/by-validity/{valid}")
    public List<Reservation> getValidReservations(@PathVariable String valid) {
        return reservationService.findAll().stream().filter(res->{
            if(valid.equals("true") || valid.equals("false")){
                return res.getIsValid().toString().equals(valid);
            }else{
                return false;
            }
        }).toList();
    }
    @GetMapping("/get/by-date/{date}")
    public List<Reservation> getReservationsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return reservationService.findAll().stream().filter(res -> {
            return Objects.equals(res.getYear().toString(), date.toString()) && res.getYear().getMonth() == date.getMonth() && res.getYear().getDate() == date.getDate();
        }).toList();
    }
    @GetMapping("/get/{id}/students")
    public Optional<List<Student>> getStudentsByReservationId(@PathVariable UUID id) {
        try{
            Optional<Reservation> reservation = reservationService.findReservationById(id);
            return Optional.ofNullable(reservation.get().getStudents());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/add")
    public String addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
        return "Reservation added";
    }
    @PutMapping("/edit/{id}")
    public String editReservation(@RequestBody Reservation reservation,@PathVariable UUID id) {
        Optional<Reservation> foundReservation = reservationService.findReservationById(id);
        if(foundReservation.isPresent()) {
            foundReservation.get().setIsValid(reservation.getIsValid());
            foundReservation.get().setYear(reservation.getYear());
            reservationService.save(foundReservation.get());
            return "Reservation updated";
        }else{
            return "Reservation Not Found";
        }
    }
    @PutMapping("/edit/{studentId}/{reservationId}")
    public String editUserReservationState(@PathVariable UUID studentId, @PathVariable UUID reservationId) {
        Optional<Reservation> foundReservation = reservationService.findReservationById(reservationId);
        if(foundReservation.isPresent()) {
            Optional<Student> foundStudent = userService.getUserById(studentId);
            if(foundStudent.isPresent()) {
                if(foundReservation.get().getStudents().contains(foundStudent.get())) {
                    foundReservation.get().getStudents().remove(foundStudent.get());
                    foundStudent.get().getReservations().add(foundReservation.get());
                    reservationService.save(foundReservation.get());
                    userService.updateStudent(foundStudent.get());
                    return "user removed from reservation list";
                }else{
                    foundReservation.get().getStudents().add(foundStudent.get());
                    foundStudent.get().getReservations().add(foundReservation.get());
                    reservationService.save(foundReservation.get());
                    userService.updateStudent(foundStudent.get());
                    return "user added to reservation list";
                }
            }else{
                return "User Not Found";
            }
        }else{
            return "reservation Not Found";
        }
    }
}
