package com.example.libraryapi.book.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.common.exception.BookQuantityOverException;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("책을 새로 저장한다")
    void saveBook() {
        // given
        final Book book = Book.builder()
                              .title("test book")
                              .author("test author")
                              .publisher("test publisher")
                              .price(10000)
                              .isbn("192832110223")
                              .quantity(3)
                              .build();

        // when
        Book savedBook = bookService.registerBook(book);

        // then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(savedBook.getPublisher()).isEqualTo(book.getPublisher());
        assertThat(savedBook.getPrice()).isEqualTo(book.getPrice());
        assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(savedBook.getQuantity()).isEqualTo(book.getQuantity());
    }

    @Test
    @DisplayName("책을 id로 조회한다")
    void getBookById() {
        // given
        final Long bookId = 1L;

        // when
        Book book = bookService.findById(bookId);

        // then
        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(bookId);
        assertThat(book.getTitle()).isEqualTo("book1");
        assertThat(book.getAuthor()).isEqualTo("author1");
    }



    @Test
    @DisplayName("책을 폐기한다.")
    void discard() {
        // given
        final Book book = bookService.findById(1L);
        final int originQuantity = book.getQuantity();

        // when
        final int discardQuantity = 5;
        final Book discard = bookService.discard(book.getId(), discardQuantity);

        // then
        assertThat(discard).isNotNull();
        assertThat(discard.getId()).isEqualTo(book.getId());
        assertThat(discard.getQuantity()).isEqualTo(originQuantity - discardQuantity);
    }

    @Test
    @DisplayName("수량 보다 넘는 책을 폐기하여 예외가 발생한다.")
    void bookQuantityOverException() {
        // given
        final Book book = bookService.findById(1L);
        final int originQuantity = book.getQuantity();

        // when
        final BookQuantityOverException bookQuantityOverException = assertThrows(
                BookQuantityOverException.class, () -> {
                    final int discardQuantity = originQuantity + 1;
                    final Book discard = bookService.discard(book.getId(), discardQuantity);

                });

        // then
        assertThat(bookQuantityOverException.getMessage()).isEqualTo("폐기할 수량이 현재 도서관에 보유한 수량보다 많습니다.");
    }

}
