package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepo extends JpaRepository<Room, UUID> {
    Optional<Room> findRoomById(UUID id);
    Optional<Room> findRoomByBlock(Block block);
    Optional<Room> findRoomByRoomNumber(Long id);
    Optional<Room> findRoomByTypeR(RoomType type);
    List<Room> findRoomsByBlockId(UUID blockId);
    List<Room> findRoomsByTypeR(RoomType type);
    Optional<Room> findById(UUID roomId);
}