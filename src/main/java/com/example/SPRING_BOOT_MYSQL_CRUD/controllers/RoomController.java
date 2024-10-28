package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.ReservationService;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService = new RoomService();
    @Autowired
    private ReservationService reservationService = new ReservationService();

    @GetMapping("/get")
    List<Room> getAllRooms() {
        return roomService.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<Room> getRoomById(@PathVariable UUID id) {
        return roomService.findRoomById(id);
    }
    @GetMapping("/{id}/reservations")
    List<Reservation> getAllReservationsByRoomId(@PathVariable UUID id) {
        return roomService.findReservationsByRoomId(id);
    }
    @PutMapping("/edit/room/{roomId}/reservations/{reservationId}")
    public Reservation editReservationViaRoomId(@PathVariable UUID roomId, @PathVariable UUID reservationId) {
        Optional<Room> room = roomService.findRoomById(roomId);
        if (room.isPresent()) {
            Optional<Reservation> foundReservation = reservationService.findReservationById(reservationId);
            if (foundReservation.isPresent()) {
                foundReservation.get().setYear(foundReservation.get().getYear());
                foundReservation.get().setIsValid(foundReservation.get().getIsValid());
                return reservationService.save(foundReservation.get());
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomService.save(room);
    }
    @PutMapping("/edit/{id}")
    public Room editRoom(@PathVariable UUID id, @RequestBody Room room) {
        Optional<Room> foundRoom = roomService.findRoomById(id);
        if (foundRoom.isPresent()) {
            return roomService.save(room);
        }else{
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable UUID id) {
        return roomService.deleteRoomById(id);
    }
}
