package com.example.libraryapi.book.entity;

import com.example.libraryapi.common.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false, unique = true)
    private String isbn; // 국제 표준 도서번호

    private int price;

    private int quantity; // 실제 가지고 있는 수량

    private int availableQuantity; // 대여 가능한 수량

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @AllArgsConstructor
    public enum Status {
        AVAILABLE("대여 가능"),
        UNAVAILABLE("대여 불가");

        private final String description;
    }

    @Builder
    public Book(String title, String author, String publisher, String isbn, int price, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
        this.availableQuantity = quantity;
        this.status = Status.AVAILABLE;
    }

    void addQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("quantity 는 0보다 커야 합니다.");
        }
        this.quantity += quantity;
        this.availableQuantity += quantity;
    }

    void deleteQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("quantity 는 0보다 커야 합니다.");
        }
        this.quantity -= quantity;
        this.availableQuantity -= quantity;
    }

}
