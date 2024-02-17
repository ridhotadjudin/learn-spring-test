package com.nexsoft.spring3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexsoft.spring3.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> { // nama class, primary key

	Optional<Book> findByBookName(String bookName);
	Optional<Book> findByAuthor(String author);
	Optional<Book> findByPublisher(String publisher);
	Optional<Book> findByTahunTerbit(String tahunTerbit);
}
