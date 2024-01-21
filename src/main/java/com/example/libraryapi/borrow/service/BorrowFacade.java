package com.example.libraryapi.borrow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.service.BookService;
import com.example.libraryapi.borrow.dto.BorrowBookRequestDto;
import com.example.libraryapi.borrow.dto.BorrowRequestDto;
import com.example.libraryapi.borrow.dto.ReturnBookRequestDto;
import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;
import com.example.libraryapi.customer.entity.Customer;
import com.example.libraryapi.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowFacade {
    private final BorrowService borrowService;
    private final BookService bookService;
    private final CustomerService customerService;
    private final RedissonClient redissonClient;

    @Value("${book.key-prefix}")
    private String bookKeyPrefix;

    public Borrow borrow(BorrowRequestDto requestDto) {
        final RLock lock = redissonClient.getLock(bookKeyPrefix);

        try {
            if (!lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                log.error("lock 회득 실패하였습니다.");
                throw new RuntimeException("lock 회득 실패하였습니다.");
            }

            final Customer custom = customerService.findById(requestDto.getCustomerId());
            final List<BorrowBook> books = getBorrowBooks(requestDto);
            return borrowService.borrow(books, custom);
        } catch (InterruptedException e) {
            log.error("InterruptedException 에러가 발생했습니다.", e);
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return Borrow.builder().build();
    }

    public Borrow returnBook(ReturnBookRequestDto requestDto) {
        final RLock lock = redissonClient.getLock(bookKeyPrefix);

        try {
            if (!lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                log.error("lock 회득 실패하였습니다.");
                throw new RuntimeException("lock 회득 실패하였습니다.");
            }
            final Borrow borrow = borrowService.findByIdAndCustomerId(requestDto.getId(), requestDto.getCustomerId());
            final List<BorrowBook> returnBooks = getReturnBooks(requestDto, borrow);
            return borrowService.returnBook(borrow, returnBooks);
        } catch (InterruptedException e) {
            log.error("InterruptedException 에러가 발생했습니다.", e);
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return Borrow.builder().build();
    }



    private List<BorrowBook> getBorrowBooks(BorrowRequestDto requestDto) {
        final List<BorrowBookRequestDto> borrows = requestDto.getBorrowBooks();
        List<BorrowBook> books = new ArrayList<>();
        for (BorrowBookRequestDto dto : borrows) {
            final Book book = bookService.findById(dto.getBookId());
            final BorrowBook borrowBook = BorrowBook.createBorrowBook(book, dto.getQuantity());
            books.add(borrowBook);
        }
        return books;
    }

    private List<BorrowBook> getReturnBooks(ReturnBookRequestDto requestDto, Borrow borrow) {
        final List<BorrowBook> borrowBooks = borrow.getBorrowBooks();
        List<BorrowBook> returnBooks = new ArrayList<>();

        for (BorrowBook borrowBook : borrowBooks) {
            requestDto.getReturnBooks()
                      .stream()
                      .filter(dto -> borrowBook.getBook().getId().equals(dto.getBookId()))
                      .map(dto -> borrowBook)
                      .forEachOrdered(returnBooks::add);
        }

        return returnBooks;
    }

}
