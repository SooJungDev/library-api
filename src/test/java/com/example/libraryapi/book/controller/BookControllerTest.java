package com.example.libraryapi.book.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("도서 등록 API 테스트")
    void registerBook() throws Exception {
        mockMvc.perform(post("/library/api/book")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"title\":\"테스트 책 이름\",\"author\":\"테스트 책 저자\", \"publisher\": \"테스트 출판사\",\"isbn\": \"1234567890\",\"price\":10000,\"quantity\":10}"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.title").value("테스트 책 이름"))
               .andExpect(jsonPath("$.author").value("테스트 책 저자"))
               .andExpect(jsonPath("$.publisher").value("테스트 출판사"))
               .andExpect(jsonPath("$.isbn").value("1234567890"))
               .andExpect(jsonPath("$.price").value(10000))
               .andExpect(jsonPath("$.quantity").value(10))
               .andExpect(jsonPath("$.availableQuantity").value(10))
               .andExpect(jsonPath("$.bookStatus").value("AVAILABLE"))
               .andExpect(jsonPath("$.bookStatusDescription").value("대여 가능"));

    }

    @Test
    @DisplayName("도서 id로 조회 API 테스트")
    void getBookById() throws Exception {
        mockMvc.perform(get("/library/api/book/1"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.title").value("book1"))
               .andExpect(jsonPath("$.author").value("author1"))
               .andExpect(jsonPath("$.publisher").value("publisher1"))
               .andExpect(jsonPath("$.isbn").value("1234567891111"))
               .andExpect(jsonPath("$.price").value(16800))
               .andExpect(jsonPath("$.quantity").value(5))
               .andExpect(jsonPath("$.availableQuantity").value(5))
               .andExpect(jsonPath("$.bookStatus").value("AVAILABLE"))
               .andExpect(jsonPath("$.bookStatusDescription").value("대여 가능"));
    }

    @Test
    @DisplayName("도서 주문 API 테스트")
    void orderBook() throws Exception {
        mockMvc.perform(patch("/library/api/book/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":1,\"quantity\":1}"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.title").value("book1"))
               .andExpect(jsonPath("$.author").value("author1"))
               .andExpect(jsonPath("$.publisher").value("publisher1"))
               .andExpect(jsonPath("$.isbn").value("1234567891111"))
               .andExpect(jsonPath("$.price").value(16800))
               .andExpect(jsonPath("$.quantity").value(6))
               .andExpect(jsonPath("$.availableQuantity").value(6))
               .andExpect(jsonPath("$.bookStatus").value("AVAILABLE"))
               .andExpect(jsonPath("$.bookStatusDescription").value("대여 가능"));
    }

    @Test
    @DisplayName("도서 폐기 API 테스트")
    void discardBook() throws Exception {
        mockMvc.perform(patch("/library/api/book/discard")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":1,\"quantity\":1}"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.title").value("book1"))
               .andExpect(jsonPath("$.author").value("author1"))
               .andExpect(jsonPath("$.publisher").value("publisher1"))
               .andExpect(jsonPath("$.isbn").value("1234567891111"))
               .andExpect(jsonPath("$.price").value(16800))
               .andExpect(jsonPath("$.quantity").value(4))
               .andExpect(jsonPath("$.availableQuantity").value(4))
               .andExpect(jsonPath("$.bookStatus").value("AVAILABLE"))
               .andExpect(jsonPath("$.bookStatusDescription").value("대여 가능"));
    }

}
