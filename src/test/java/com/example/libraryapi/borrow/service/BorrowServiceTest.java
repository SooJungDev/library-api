package com.example.libraryapi.borrow.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.respository.BookRepository;
import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;
import com.example.libraryapi.common.exception.BookQuantityOverException;
import com.example.libraryapi.customer.entity.Customer;
import com.example.libraryapi.customer.respository.CustomerRepository;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BorrowServiceTest {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    private Customer customer;

    private Book book;

    @BeforeEach
    void before() throws Exception {
        customer = customerRepository.findById(1L).get();
        book = bookRepository.findById(1L).get();
    }

    @Test
    @DisplayName("책을 대여한다")
    void borrow() {
        // given
        int borrowBookQuantity = 1;
        List<BorrowBook> borrowBooks = List.of(BorrowBook.createBorrowBook(book, borrowBookQuantity));

        // when
        final Borrow result = borrowService.borrow(borrowBooks, customer);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCustomer()).isEqualTo(customer);
        assertThat(result.getTotalBorrowBookQuantity()).isEqualTo(borrowBookQuantity);
    }

    @Test
    @DisplayName("책을 대여할 수량보다 도서관에 재고가 모자르면 예외가 발생한다")
    void bookQuantityOverException() {
        // given
        // when
        final BookQuantityOverException bookQuantityOverException = assertThrows(
                BookQuantityOverException.class, () -> {
                    int borrowBookQuantity = 100;
                    List<BorrowBook> borrowBooks = List.of(BorrowBook.createBorrowBook(book, borrowBookQuantity));
                    borrowService.borrow(borrowBooks, customer);
                });

        // then
        assertThat(bookQuantityOverException.getMessage()).isEqualTo("대여할 수량이 현재 도서관에 보유한 수량보다 많습니다.");
    }

    @Test
    @DisplayName("책을 반납한다")
    void returnBook() {
        // given
        int borrowBookQuantity = 1;
        List<BorrowBook> borrowBooks = List.of(BorrowBook.createBorrowBook(book, borrowBookQuantity));
        final Borrow borrow = borrowService.borrow(borrowBooks, customer);

        // when
        final List<BorrowBook> returnBooks = borrow.getBorrowBooks();
        final Borrow result = borrowService.returnBook(borrow, returnBooks);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCustomer()).isEqualTo(customer);
        assertThat(result.getTotalBorrowBookQuantity()).isEqualTo(0);
    }

}
