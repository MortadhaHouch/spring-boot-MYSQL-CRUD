package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foyer")
public class ReservationController {
    @Autowired
    ReservationService reservationService =  new ReservationService();
    @GetMapping("/get")
    public List<Reservation> getReservations() {
        return reservationService.findAll();
    }
}
