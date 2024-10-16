package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    @Autowired
    RoomRepo roomRepo;
    public List<Reservation> findReservationsByRoomId(UUID roomId) {
        Optional<Room> optionalRoom = roomRepo.findById(roomId);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().getReservations();
        }else{
            return List.of();
        }
    };
    public Room save(Room room){
        return roomRepo.save(room);
    }
    public Optional<Room> findRoomById(UUID roomId){
        return roomRepo.findById(roomId);
    }
    public List<Room> findAll(){
        return roomRepo.findAll();
    }
    public String deleteRoomById(UUID roomId){
        Optional<Room> optionalRoom = roomRepo.findById(roomId);
        if(optionalRoom.isPresent()){
            roomRepo.deleteById(roomId);
            return "Room deleted successfully";
        }else{
            return "Room not found";
        }
    }
}
