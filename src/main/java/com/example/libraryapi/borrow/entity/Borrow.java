package com.example.libraryapi.borrow.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.libraryapi.common.entity.AbstractEntity;
import com.example.libraryapi.customer.entity.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "borrow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Borrow extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long id;

    @OneToMany(mappedBy = "borrow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowBook> borrowBooks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_id")
    private Customer customer;

    private int totalBorrowBookQuantity; // 총 대여 수량

    @Column(name = "status")
    private BorrowStatus status;

    @Builder
    public Borrow(List<BorrowBook> borrowBooks, Customer customer, int totalBorrowBookQuantity,
                  BorrowStatus status) {
        this.borrowBooks = borrowBooks;
        this.customer = customer;
        this.totalBorrowBookQuantity = totalBorrowBookQuantity;
        this.status = status;
    }

    public static Borrow createBorrow(List<BorrowBook> borrowBooks, Customer customer) {
        Borrow borrow = new Borrow();
        for (BorrowBook borrowBook : borrowBooks) {
            borrow.borrowBooks.add(borrowBook);
            borrowBook.setBorrow(borrow);
            borrow.totalBorrowBookQuantity += borrowBook.getQuantity();
        }
        borrow.status = BorrowStatus.BORROW;
        borrow.customer = customer;
        return borrow;
    }

    public void returnBorrowBook(List<BorrowBook> returnBooks) {
        for (BorrowBook returnBook : returnBooks) {
            returnBook.returnBook();
            this.totalBorrowBookQuantity -= returnBook.getQuantity();
        }

        if (this.totalBorrowBookQuantity == 0) {
            this.status = BorrowStatus.RETURN;
        }
    }
}
