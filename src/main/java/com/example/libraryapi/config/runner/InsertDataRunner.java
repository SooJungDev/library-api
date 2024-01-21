package com.example.libraryapi.config.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.book.respository.BookRepository;
import com.example.libraryapi.customer.entity.Customer;
import com.example.libraryapi.customer.respository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Profile({"local", "test"})
@Component
@RequiredArgsConstructor
public class InsertDataRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    private final BookRepository bookRespository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().name("홍길동").phone("010-1234-5678").address("서울시 강남구").build());
        customers.add(Customer.builder().name("아무개").phone("010-4567-8910").address("서울시 동작구").build());
        customerRepository.saveAll(customers);

        List<Book> books = new ArrayList<>();
        books.add(Book.builder().title("book1").author("author1").publisher("publisher1").isbn("1234567891111").price(16800).quantity(5).build());
        books.add(Book.builder().title("book2").author("author2").publisher("publisher2").isbn("1234567892222").price(18800).quantity(1).build());
        books.add(Book.builder().title("book3").author("author3").publisher("publisher3").isbn("1234567893333").price(20000).quantity(2).build());
        books.add(Book.builder().title("book4").author("author4").publisher("publisher4").isbn("1234567894444").price(30800).quantity(3).build());
        books.add(Book.builder().title("book5").author("author5").publisher("publisher5").isbn("1234567895555").price(40000).quantity(8).build());
        books.add(Book.builder().title("book6").author("author6").publisher("publisher6").isbn("1234567896666").price(50000).quantity(1).build());
        bookRespository.saveAll(books);
    }
}
