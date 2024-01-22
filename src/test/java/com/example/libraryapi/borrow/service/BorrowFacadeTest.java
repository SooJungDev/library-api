package com.example.libraryapi.borrow.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BorrowFacadeTest {

    @Autowired
    private BorrowFacade borrowFacade;

    @BeforeEach
    void before() throws Exception {
    }

    @Test
    @DisplayName("도서 대출이 정상적으로 처리되는지 확인한다.")
    void borrow() {
    }

    @Test
    @DisplayName("도서 대출 수량이 넘었을때 예외가 발생하는지 확인한다.")
    void borrowOverCount() {
    }

    @Test
    @DisplayName("멀티스레드 환경에서 도서 대출이 정상적으로 처리되는지 확인한다.")
    void borrowMultiThread() {
    }

    @Test
    @DisplayName("멀티스레드 환경에서 도서 대출 수량이 넘었을때 예외가 발생하는지 확인한다.")
    void borrowOverCountMultiThread() {
    }

    @Test
    @DisplayName("도서 반납이 정상적으로 처리되는지 확인한다.")
    void returnBook() {
    }

}
