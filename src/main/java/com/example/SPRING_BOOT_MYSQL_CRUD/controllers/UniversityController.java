package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    UniversityService universityService = new UniversityService();
    @GetMapping("/get")
    public List<University> getAllUniversities(){
        return universityService.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<University> getUniversityById(@PathVariable UUID id){
        return universityService.findUniversityById(id);
    }
    @GetMapping("/get/by-address/{address}")
    public List<University> getUniversitiesByAddress(@PathVariable String address){
        return universityService.findByAddress(address);
    }
    @GetMapping("/get/{id}/foyer")
    public Optional<List<Foyer>> findFoyersByUniversityId(@PathVariable UUID id){
        Optional<University> foundUniversity = universityService.findUniversityById(id);
        return Optional.ofNullable(foundUniversity.get().getFoyer());
    }
    @PostMapping("/add")
    public University addUniversity(@RequestBody University university){
        return universityService.save(university);
    }
    @PutMapping("/edit/{id}")
    public University updateUniversity(@RequestBody University university,@PathVariable UUID id){
        Optional<University> foundUniversity = universityService.findUniversityById(id);
        if(foundUniversity.isPresent()){
            university.setName(university.getName());
            university.setAddress(university.getAddress());
            universityService.save(university);
            return university;
        }else{
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUniversityById(@PathVariable UUID id){
        return universityService.deleteUniversity(id);
    }
}
