package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService = new UserService();
    @GetMapping("/get")
    public Map<Integer,List<Student>> getStudents(@RequestParam(required = false) Optional<Integer> p) {
        List<Student> students = userService.getUsers();
        HashMap<Integer,List<Student>> map = new HashMap<>();
        if(students.size() > 10 && p.isPresent()) {
            map.put(p.get(),students.stream().skip(p.get() * 10L).limit(10).toList());
        }else{
            map.put(0,students);
        }
        return map;
    }
    @GetMapping("/get/{id}")
    public Optional<Student> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }
    @GetMapping("/get/{id}/reservations")
    public Map<Integer,List<Reservation>> getStudentReservations(@PathVariable UUID id,@RequestParam(required = false) Optional<Integer> p) {
        Optional<Student> foundStudent = userService.getUserById(id);
        HashMap<Integer,List<Reservation>> map = new HashMap<>();
        if(foundStudent.isPresent()){
            if(foundStudent.get().getReservations().size() > 10 && p.isPresent()) {
                map.put(p.get(),foundStudent.get().getReservations().stream().skip(p.get() * 10L).limit(10).toList());
            }else{
                map.put(0,foundStudent.get().getReservations().stream().toList());
            }
            return map;
        }else {
            return map;
        }
    }
    @GetMapping("/get/{id}/reservations/{reservationId}")
    public Optional<Reservation> getStudentReservation(@PathVariable UUID id, @PathVariable Long reservationId) {
        Optional<Student> foundStudent = userService.getUserById(id);
        return foundStudent.map(student -> Optional.of((Reservation) student.getReservations().stream().filter(reservation -> {
            return reservation.getId().equals(reservationId);
        }))).orElse(null);
    }
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return userService.addStudent(student);
    }
    @PutMapping("/edit/{id}")
    public Student editStudent(@RequestBody Student student) {
        return userService.updateStudent(student);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@RequestBody UUID studentId) {
        return userService.deleteStudent(studentId);
    }
}
