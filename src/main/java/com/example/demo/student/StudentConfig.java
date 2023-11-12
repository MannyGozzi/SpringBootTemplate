package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student chris = new Student(
                    1L,
                    "chris",
                    "chris@gmail.com",
                    LocalDate.of(2002, Month.JANUARY, 1)
            );

            Student josh = new Student(
                    "josh",
                    "josh@gmail.com",
                    LocalDate.of(2000, Month.MARCH, 2)
            );

            repository.saveAll(List.of(chris, josh));
        };
    }
}
