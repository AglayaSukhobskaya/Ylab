package com.edu.ulab.app.repository;

import com.edu.ulab.app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
