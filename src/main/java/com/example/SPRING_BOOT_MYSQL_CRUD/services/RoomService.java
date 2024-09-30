package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    RoomRepo roomRepo;
    public List<Reservation> findReservationsByRoomId(Long roomId) {
        Optional<Room> optionalRoom = roomRepo.findById(roomId);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().getReservations();
        }else{
            return List.of();
        }
    };
}
