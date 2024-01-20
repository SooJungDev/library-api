package com.example.libraryapi.borrow.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryapi.borrow.entity.BorrowBook;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
}
