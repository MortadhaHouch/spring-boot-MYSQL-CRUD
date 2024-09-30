package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.FoyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoyerService {
    @Autowired
    FoyerRepo foyerRepo;
    List<Block> findBlocksByFoyerId(int id) {
        Optional<Foyer> foundFoyer = foyerRepo.findById(id);
        if(foundFoyer.isPresent()){
            return foundFoyer.get().getBlocks();
        }else{
            return List.of();
        }
    }
    Optional<University> findUniversityByFoyerId(int id) {
        Optional<Foyer> foundFoyer = foyerRepo.findById(id);
        return foundFoyer.map(Foyer::getUniversity);
    }
}
