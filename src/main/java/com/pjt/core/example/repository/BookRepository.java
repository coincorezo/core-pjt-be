package com.pjt.core.example.repository;

import com.pjt.core.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
