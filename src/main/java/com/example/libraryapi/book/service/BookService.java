package com.example.libraryapi.book.service;

import com.example.libraryapi.book.entity.Book;

public interface BookService {

    Book registerBook(Book book);

    Book findById(Long id);

    Book order(Long id, int quantity);

    Book discard(Long id, int quantity);
}
