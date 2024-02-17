package com.nexsoft.spring3.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="book_name",nullable=false)
	private String bookName;
	
	@Column(name="author",nullable=false)
	private String author;
	
	@Column(name="publisher",nullable=false)
	private String publisher;
	
	@Column(name="tahun_terbit",nullable=false)
	private String tahunTerbit;
}
