package com.example.libraryapi.borrow.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libraryapi.borrow.entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
