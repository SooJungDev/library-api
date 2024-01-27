package com.example.libraryapi.book.entity;

import java.time.ZonedDateTime;

import com.example.libraryapi.common.entity.AbstractEntity;

import jakarta.persistence.Column;
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

@Getter
@Entity
@Table(name = "order_book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBook extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private ZonedDateTime orderAt;

    @Builder
    public OrderBook(Book book, int quantity, OrderType orderType) {
        this.book = book;
        this.quantity = quantity;
        this.orderType = orderType;
        this.status = OrderStatus.WAIT;
    }

    @Getter
    @AllArgsConstructor
    public enum OrderStatus {
        SUCCESS("주문 완료"),
        WAIT("주문 대기"),
        FAIL("주문 실패");
        private final String description;
    }

    public static OrderBook createOrderBook(Book book, int quantity, OrderType orderType,
                                            ZonedDateTime orderAt) {
        if (quantity < 1) {
            throw new IllegalArgumentException("최소 1개 이상의 책을 주문해야 합니다.");
        }

        if (orderAt != null && orderAt.isBefore(ZonedDateTime.now())) {
            throw new IllegalArgumentException("미래의 시간을 입력해주세요.");
        }

        OrderBook orderBook = new OrderBook();
        orderBook.quantity = quantity;
        orderBook.orderType = orderType;
        orderBook.status = OrderStatus.WAIT;

        if (orderType == OrderType.NOW) {
            System.out.println("orderBook NOW = " + orderBook);
            book.orderBook(quantity);
            orderBook.status = OrderStatus.SUCCESS;
        }

        if (orderType == OrderType.RESERVATION) {
            orderBook.orderAt = orderAt;
        }
        orderBook.book = book;
        return orderBook;
    }

    public void reserveBook() {
        if (this.orderType != OrderType.RESERVATION) {
            throw new IllegalArgumentException("예약 주문이 아닙니다.");
        }
        this.status = OrderStatus.SUCCESS;
        this.book.orderBook(this.quantity);
    }
}
