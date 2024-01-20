package com.example.libraryapi.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.book.BookMapper;
import com.example.libraryapi.book.dto.BookRequestDto;
import com.example.libraryapi.book.dto.BookResponseDto;
import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/library/api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable("id") Long id) {
        final Book book = bookService.findById(id);
        return ResponseEntity.ok(bookMapper.of(book));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> registerBook(@Valid @RequestBody BookRequestDto requestDto) {
        final Book book = bookMapper.of(requestDto);
        final Book savedBook = bookService.registerBook(book);
        final BookResponseDto bookResponseDto = bookMapper.of(savedBook);
        return ResponseEntity.ok(bookResponseDto);
    }

}
