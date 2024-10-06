package com.example.SPRING_BOOT_MYSQL_CRUD.services;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Reservation;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.ReservationRepo;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ReservationRepo reservationRepo;
    public Student addStudent(Student student) {
        return userRepo.save(student);
    }
    public Student updateStudent(Student student) {
       Optional<Student> foundStudent = userRepo.findById(student.getId());
       if(foundStudent.isPresent()){
           foundStudent.get().setDob(student.getDob());
           foundStudent.get().setCIN(student.getCIN());
           foundStudent.get().setFirstName(student.getFirstName());
           foundStudent.get().setLastName(student.getLastName());
           foundStudent.get().setReservations(student.getReservations());
           return userRepo.save(foundStudent.get());
       }else{
           return null;
       }
    }
    public List<Student> getUsers() {
        return userRepo.findAll();
    }
    public Optional<Student> getUserById(UUID id) {
        return userRepo.findById(id);
    }
    public Optional<List<Student>> findUsersByReservationId(UUID reservationId) {
        Optional<Reservation> foundReservation = reservationRepo.findById(reservationId);
        return Optional.ofNullable(foundReservation.get().getStudents());
    }
    public Optional<List<Reservation>> findReservationsByStudentId(UUID studentId) {
        Optional<Student> foundStudent = userRepo.findById(studentId);
        return Optional.ofNullable(foundStudent.get().getReservations());
    }
    public String deleteStudent(UUID studentId) {
        Optional<Student> foundStudent = userRepo.findById(studentId);
        if(foundStudent.isPresent()){
            userRepo.delete(foundStudent.get());
            return "Student deleted successfully";
        }else{
            return "Student not found";
        }
    }
}
