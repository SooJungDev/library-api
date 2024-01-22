package com.example.libraryapi.borrow.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("도서 대출 API 테스트")
    void borrowBook() throws Exception {
    }

    @Test
    @DisplayName("도서 반납 API 테스트")
    void returnBook() throws Exception {
    }

}
