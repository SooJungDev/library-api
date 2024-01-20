package com.example.libraryapi.book.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.respository.BookRespository;
import com.example.libraryapi.common.exception.BookNotFoundException;
import com.example.libraryapi.config.RedissonConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRespository bookRespository;
    private final RedissonConfig redissonConfig;

    @Transactional
    @Override
    public Book registerBook(Book book) {
        if(bookRespository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("isbn 이 존재합니다. 이미 등록된 책입니다.");
        }
        return bookRespository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Long id) {
        return bookRespository.findById(id).orElseThrow(
                () -> new BookNotFoundException(String.format("책 아이디가 %d 인 책이 존재하지 않습니다.", id)));
    }

    @Override
    public Book order(Long id, int quantity) {
        return null;
    }

    @Override
    public Book discard(Long id, int quantity) {
        return null;
    }

}
