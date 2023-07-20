package com.cg5labs.api.service;

import com.cg5labs.api.model.Student;
import com.cg5labs.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){

        List<Student> students = new ArrayList<>();

        studentRepository.findAll().forEach(students::add);

        return students;
    }

    public Optional<Student> getSingleStudent(Long id) {
        boolean exist = studentRepository.existsById(id);

        if(!exist){
            throw new IllegalStateException("Student id: " + id + " does not exist");
        }

        return studentRepository.findById(id);
    }

    public void createStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email is already taken !!!");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("student id: " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    public void updateStudent(Long id, Student student) {
       Student studentExist = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student id: " + id + " does not exist"));

       studentExist.setName(student.getName());
       studentExist.setAge(student.getAge());
       studentExist.setDob(student.getDob());
       studentExist.setEmail(student.getEmail());  // should add validation for email

       studentRepository.save(studentExist);
    }
}
