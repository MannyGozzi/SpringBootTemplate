package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // add this in order to get dependency injection
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional // specifies that all changes to database should be committed
    public void updateStudent(Long studentId, String name, String email) {
        System.out.println("Replacing " + studentId + " " + name + " " + email);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
       if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
           if (studentOptional.isPresent()) {
               throw new IllegalStateException("email taken");
           }
           student.setEmail(email);
       }
    }
}
