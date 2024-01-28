package com.example.libraryapi.borrow.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    @DisplayName("도서 대출 API 테스트")
    void borrowBook() throws Exception {
        mockMvc.perform(post("/books/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"customerId\": 1, \"borrowBooks\": [ {\"bookId\": 1,\"quantity\": 1},{ \"bookId\": 2, \"quantity\": 1} ]}"
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.borrowBooks").isArray())
                .andExpect(jsonPath("$.borrowBooks").isNotEmpty())
                .andExpect(jsonPath("$.borrowBooks[0].id").value(1))
                .andExpect(jsonPath("$.borrowBooks[0].quantity").value(1))
                .andExpect(jsonPath("$.totalBorrowBookQuantity").value(2))
                .andExpect(jsonPath("$.status").value("BORROW"))
                .andExpect(jsonPath("$.statusDescription").value("대여 중"));
    }

    @Order(2)
    @Test
    @DisplayName("도서 반납 API 테스트")
    void returnBook() throws Exception {
        mockMvc.perform(post("/books/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"id\": 1, \"customerId\": 1, \"returnBooks\": [ {\"bookId\": 1,\"quantity\": 1},{ \"bookId\": 2, \"quantity\": 1} ]}"
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.borrowBooks").isArray())
                .andExpect(jsonPath("$.borrowBooks").isNotEmpty())
                .andExpect(jsonPath("$.borrowBooks[0].id").value(1))
                .andExpect(jsonPath("$.borrowBooks[0].quantity").value(1))
                .andExpect(jsonPath("$.totalBorrowBookQuantity").value(0))
                .andExpect(jsonPath("$.status").value("RETURN"))
                .andExpect(jsonPath("$.statusDescription").value("반납 완료"));
    }

}
