package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    /*List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1L, "Ayberk", "kurtknispel9@gmail.com", LocalDate.of(1999, Month.MARCH, 4), 24)
    ));*/
    private final StudentRepository studentRepository ;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}
