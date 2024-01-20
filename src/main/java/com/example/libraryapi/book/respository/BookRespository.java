package com.example.libraryapi.book.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryapi.book.entity.Book;

@Repository
public interface BookRespository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
