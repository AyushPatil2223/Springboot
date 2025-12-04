// package com.example.demo.service;

// import java.util.List;
// import org.springframework.stereotype.Service;
// import com.example.demo.model.Student;
// import com.example.demo.repository.StudentRepository;

// @Service
// public class StudentService {
//     private final StudentRepository repo;

//     public StudentService(StudentRepository repo) {
//         this.repo = repo;
//     }

//     public Student addStudent(Student s) {
//         return repo.save(s);
//     }

//     public List<Student> getAll() {
//         return repo.findAll();
//     }

//     public Student getById(Long id) {
//         return repo.findById(id).orElse(null);
//     }

//     public Student updateStudent(Long id, Student newData) {
//         Student old = repo.findById(id).orElse(null);

//         if (old != null) {
//             old.setName(newData.getName());
//             old.setCourse(newData.getCourse());
//             old.setAge(newData.getAge());
//             return repo.save(old);
//         }
//         return null;
//     }

//     public void deleteStudent(Long id) {
//         repo.deleteById(id);
//     }
// }
