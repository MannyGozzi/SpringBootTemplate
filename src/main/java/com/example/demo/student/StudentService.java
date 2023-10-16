package com.example.demo.student;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // add this in order to get dependency injection
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "chris",
                        "chris@gmail.com",
                        LocalDate.of(2002, Month.JANUARY, 1),
                        21
                )
        );
    }
}
