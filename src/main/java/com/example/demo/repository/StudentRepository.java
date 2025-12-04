package com.example.demo.repository;
import java.util.Optional;


import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByUsername(String username);

}

