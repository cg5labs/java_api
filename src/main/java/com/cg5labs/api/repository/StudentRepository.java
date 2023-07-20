package com.cg5labs.api.repository;

import com.cg5labs.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT * FROM studentdb WHERE email = ?
    Optional<Student> findStudentByEmail (String email);
}
