package com.example.libraryapi.borrow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapi.borrow.dto.BorrowRequestDto;
import com.example.libraryapi.borrow.dto.BorrowResponseDto;
import com.example.libraryapi.borrow.dto.ReturnBookRequestDto;
import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.mapper.BorrowMapper;
import com.example.libraryapi.borrow.service.BorrowFacade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/library/api/v1/book")
public class BorrowController {

    private final BorrowFacade borrowFacade;
    private final BorrowMapper borrowMapper;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowResponseDto> borrowBook(@Valid @RequestBody BorrowRequestDto requestDto) {
        final Borrow borrow = borrowFacade.borrow(requestDto);
        return ResponseEntity.ok(borrowMapper.of(borrow));
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowResponseDto> returnBook(@Valid @RequestBody ReturnBookRequestDto requestDto) {
        final Borrow borrow = borrowFacade.returnBook(requestDto);
        return ResponseEntity.ok(borrowMapper.of(borrow));
    }
}
