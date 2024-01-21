package com.example.libraryapi.book;

import com.example.libraryapi.book.dto.BookRequestDto;
import com.example.libraryapi.book.dto.BookResponseDto;
import com.example.libraryapi.book.entity.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T01:35:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book of(BookRequestDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.title( bookDto.getTitle() );
        book.author( bookDto.getAuthor() );
        book.publisher( bookDto.getPublisher() );
        book.isbn( bookDto.getIsbn() );
        book.price( bookDto.getPrice() );
        book.quantity( bookDto.getQuantity() );

        return book.build();
    }

    @Override
    public BookResponseDto of(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponseDto.BookResponseDtoBuilder bookResponseDto = BookResponseDto.builder();

        bookResponseDto.id( book.getId() );
        bookResponseDto.title( book.getTitle() );
        bookResponseDto.author( book.getAuthor() );
        bookResponseDto.publisher( book.getPublisher() );
        bookResponseDto.isbn( book.getIsbn() );
        bookResponseDto.price( book.getPrice() );
        bookResponseDto.quantity( book.getQuantity() );
        bookResponseDto.availableQuantity( book.getAvailableQuantity() );

        bookResponseDto.bookStatus( book.getStatus().name() );
        bookResponseDto.bookStatusDescription( book.getStatus().getDescription() );

        return bookResponseDto.build();
    }
}
