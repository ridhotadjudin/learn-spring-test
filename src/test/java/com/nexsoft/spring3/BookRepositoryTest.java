package com.nexsoft.spring3;

import java.util.List;

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

import com.nexsoft.spring3.model.Book;
import com.nexsoft.spring3.model.Student;
import com.nexsoft.spring3.repository.BookRepository;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest {
		
		@Autowired
		private BookRepository bookRepository;
		
		@ParameterizedTest
		@Order(1)
		@Rollback(value=false)
		@CsvFileSource(resources = "data-book.csv",  numLinesToSkip = 1)
		public void saveBook_Test(String str1,String str2,String str3,String str4) {
			Book buku = Book.builder()
					.bookName(str1)
					.author(str2)
					.publisher(str3)
					.tahunTerbit(str4)
					.build();
			bookRepository.save(buku);
			Assertions.assertThat(buku.getId()).isGreaterThan(0);
		}
		
		@Test
		@Order(1)
		public void getBook_ById_Test() {
			Book buku = bookRepository.findById(1L).get();
			Assertions.assertThat(buku.getId()).isEqualTo(1L);
		}
		
		@Test
		public void getAllBook_Test() {
			List<Book> buku = bookRepository.findAll();
			Assertions.assertThat(buku.size()).isGreaterThan(0);
		}
		
		@Test
		public void updateBook_ByBookName_Test() {
			Book buku = bookRepository.findByBookName("Ayat-ayat Cinta").get();
			buku.setAuthor("Habiburahman El-Shirazy");
			Book bookUpdated = bookRepository.save(buku);
			Assertions.assertThat(bookUpdated.getAuthor()).isEqualTo("Habiburahman El-Shirazy");
		}
		
		
		
		
}
