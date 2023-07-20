package com.cg5labs.api.config;

import com.cg5labs.api.model.Student;
import com.cg5labs.api.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student michael = new Student(
                        "Michael Michaelo",
                        32, "michael.michaelo@gmail.com",
                        LocalDate.of(1989, 02, 21)
                );
            Student stranger = new Student(
                        "Stranger Danger",
                        1, "stranger@gmail.com",
                        LocalDate.of(2020, 04, 30)
                );

            studentRepository.saveAll(List.of(michael, stranger));
        };
    }
}
