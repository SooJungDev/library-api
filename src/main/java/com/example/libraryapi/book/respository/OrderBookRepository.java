package com.example.libraryapi.book.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.libraryapi.book.entity.OrderBook;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    @Query("SELECT o FROM OrderBook o WHERE o.orderType = 'RESERVATION' and o.status = 'WAIT' and o.orderAt <= CURRENT_TIMESTAMP")
    List<OrderBook> getReservationList();
}
