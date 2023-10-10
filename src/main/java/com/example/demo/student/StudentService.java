package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exits = studentRepository.existsById(studentId);
         if(!exits){
             throw new IllegalStateException(
                     "Student ID with " + studentId + " does not exist"
             );
         }
         studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "student with id " + studentId + " does not exist"
        ));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 &&
        !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
