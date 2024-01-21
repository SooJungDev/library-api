package com.example.libraryapi.borrow.service;

import java.util.List;

import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;
import com.example.libraryapi.customer.entity.Customer;

public interface BorrowService {
    Borrow borrow(List<BorrowBook> borrowBooks, Customer customer);

    Borrow returnBook(Borrow borrow, List<BorrowBook> returnBooks);

    Borrow findByIdAndCustomerId(Long id, Long customerId);
}
