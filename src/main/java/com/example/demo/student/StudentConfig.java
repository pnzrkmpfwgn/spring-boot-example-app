package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
            return args -> {
                Student ayberk = new Student(
                        "Ayberk",
                        "kurtknispel9@gmail.com",
                        LocalDate.of(1999, Month.MARCH,4)
                );
                Student cemal = new Student(
                        "Cemal",
                        "cemaligo@gmail.com",
                        LocalDate.of(1999, Month.NOVEMBER,20)
                );
                repository.saveAll(
                        Arrays.asList(ayberk,cemal)
                );
            };
    }
}
