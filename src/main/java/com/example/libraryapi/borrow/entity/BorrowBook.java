package com.example.libraryapi.borrow.entity;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.common.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@Table(name = "borrow_book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BorrowBook extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    public static BorrowBook createBorrowBook(Book book, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("최소 1개 이상의 책을 대여해야 합니다.");
        }
        BorrowBook borrowBook = new BorrowBook();
        book.borrowBook(quantity);
        borrowBook.book = book;
        borrowBook.quantity = quantity;
        borrowBook.status = BorrowStatus.BORROW;
        return borrowBook;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public void returnBook() {
        this.book.returnBook(quantity);
        this.status = BorrowStatus.RETURN;
    }

}
