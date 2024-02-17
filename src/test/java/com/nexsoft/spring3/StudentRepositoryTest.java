package com.nexsoft.spring3;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nexsoft.spring3.model.Student;
import com.nexsoft.spring3.repository.StudentRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@ParameterizedTest
	@Order(1)
	@Rollback(value=false)
	@CsvFileSource(resources = "data-stu.csv", delimiter=',',numLinesToSkip = 1)
	public void saveStudentTest(String str1,String str2,String str3, String str4) {
		Student student = Student.builder()
				.firstName(str1)
				.lastName(str2)
				.email(str3)
				.noHp(str4)
				.build();
		studentRepository.save(student);
		Assertions.assertThat(student.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void getStudentTest() {
		Student student = studentRepository.findById(1L).get();
		Assertions.assertThat(student.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	public void getListStudentTest() {
		List<Student> student = studentRepository.findAll();
		Assertions.assertThat(student.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void updateStudentTest() {
		Student student = studentRepository.findById(1L).get();
		student.setEmail("perubahan@gmail.com");
		Student studentUpdated = studentRepository.save(student);
		Assertions.assertThat(studentUpdated.getEmail()).isEqualTo("perubahan@gmail.com");
	}
	
	@Test
	@Order(5)
	@Rollback (value=false)
	public void updateStudentByEmailTest() {
		Student student = studentRepository.findByEmail("veentii@gmail.com").get();
		student.setEmail("perubahan@gmail.com");
		Student studentUpdated = studentRepository.save(student);
		Assertions.assertThat(studentUpdated.getEmail()).isEqualTo("perubahan@gmail.com");
	}
	
	@Test
	@Order(6)
	public void getStudentByEmailTest() {
		Student student = studentRepository.findByEmail("perubahan@gmail.com").get();
		Assertions.assertThat(student.getFirstName()).isEqualTo("Venti");
	}
	
	@Test
	@Order(7)
	public void deleteStudentByEmailTest() {
		Student student = studentRepository.findByEmail("perubahan@gmail.com").get();
		studentRepository.delete(student);
		Optional<Student> optionalStudent = studentRepository.findByEmail("perubahan@gmail.com");
		Assertions.assertThat(optionalStudent).isEmpty();
	}
	
	@Test
	@Order(8)
	public void deleteStudentByIdTest() {
		Student student = studentRepository.findById(1L).get();
		studentRepository.delete(student);
		Optional<Student> optionalStudent = studentRepository.findById(1L);
		Assertions.assertThat(optionalStudent).isEmpty();
	}
	
	@Test
	@Order(9)
	public void getStudentByContactTest() {
		Student student = studentRepository.findByNoHp("08123").get();
		Assertions.assertThat(student.getEmail()).isEqualTo("ronderae@gmail.com");
	}
	
	@Test
	@Order(10)
	@Rollback (value=false)
	public void updateStudentByContactTest() {
		Student student = studentRepository.findByNoHp("08123").get();
		student.setEmail("rae@gmail.com");
		Student studentUpdated = studentRepository.save(student);
		Assertions.assertThat(studentUpdated.getEmail()).isEqualTo("rae@gmail.com");
	}
	
	@Test
	@Order(11)
	public void deleteStudentByContactTest() {
		Student student = studentRepository.findByNoHp("67890").get();
		studentRepository.delete(student);
		Optional<Student> optionalStudent = studentRepository.findByNoHp("67890");
		Assertions.assertThat(optionalStudent).isEmpty();
	}
	
}
