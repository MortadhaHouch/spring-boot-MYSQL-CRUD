package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.BlockRepo;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.FoyerRepo;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoyerService {
    @Autowired
    FoyerRepo foyerRepo;
    @Autowired
    UserRepo userRepo;
    public List<Foyer> findAll(){
        return foyerRepo.findAll();
    }
    public Optional<Foyer> findById(UUID id){
        return foyerRepo.findById(id);
    }
    public Foyer saveFoyer(Foyer foyer) {
        return foyerRepo.save(foyer);
    }
    public Optional<List<Block>> findBlocksByFoyerId(UUID id) {
        Optional<Foyer> foundFoyer = foyerRepo.findById(id);
        return Optional.ofNullable(foundFoyer.get().getBlocks());
    }
    public Optional<University> findUniversityByFoyerId(UUID id) {
        Optional<Foyer> foundFoyer = foyerRepo.findById(id);
        return foundFoyer.map(Foyer::getUniversity);
    }
    public String deleteFoyer(UUID id) {
        Optional<Foyer> foundFoyer = foyerRepo.findById(id);
        if (foundFoyer.isPresent()) {
            foyerRepo.deleteById(id);
            return "Foyer deleted";
        }else{
            return "Foyer not found";
        }
    }
}
