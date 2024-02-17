package com.nexsoft.spring3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexsoft.spring3.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> { //nama clas, primary key
	
	Optional<Student> findByEmail(String email);
	Optional<Student> findByNoHp(String noHp);
}
