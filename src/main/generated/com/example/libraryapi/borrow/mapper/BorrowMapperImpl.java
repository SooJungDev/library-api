package com.example.libraryapi.borrow.mapper;

import com.example.libraryapi.book.entity.Book;
import com.example.libraryapi.borrow.dto.BorrowBookResponseDto;
import com.example.libraryapi.borrow.dto.BorrowResponseDto;
import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-28T05:34:34+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BorrowMapperImpl implements BorrowMapper {

    @Override
    public BorrowResponseDto of(Borrow borrow) {
        if ( borrow == null ) {
            return null;
        }

        BorrowResponseDto.BorrowResponseDtoBuilder borrowResponseDto = BorrowResponseDto.builder();

        borrowResponseDto.id( borrow.getId() );
        borrowResponseDto.borrowBooks( borrowBookListToBorrowBookResponseDtoList( borrow.getBorrowBooks() ) );
        borrowResponseDto.totalBorrowBookQuantity( borrow.getTotalBorrowBookQuantity() );

        borrowResponseDto.status( borrow.getStatus().name() );
        borrowResponseDto.statusDescription( borrow.getStatus().getDescription() );

        return borrowResponseDto.build();
    }

    @Override
    public BorrowBookResponseDto of(BorrowBook borrowBook) {
        if ( borrowBook == null ) {
            return null;
        }

        BorrowBookResponseDto.BorrowBookResponseDtoBuilder borrowBookResponseDto = BorrowBookResponseDto.builder();

        borrowBookResponseDto.bookId( borrowBookBookId( borrowBook ) );
        borrowBookResponseDto.id( borrowBook.getId() );
        borrowBookResponseDto.quantity( borrowBook.getQuantity() );

        borrowBookResponseDto.status( borrowBook.getStatus().name() );
        borrowBookResponseDto.statusDescription( borrowBook.getStatus().getDescription() );

        return borrowBookResponseDto.build();
    }

    protected List<BorrowBookResponseDto> borrowBookListToBorrowBookResponseDtoList(List<BorrowBook> list) {
        if ( list == null ) {
            return null;
        }

        List<BorrowBookResponseDto> list1 = new ArrayList<BorrowBookResponseDto>( list.size() );
        for ( BorrowBook borrowBook : list ) {
            list1.add( of( borrowBook ) );
        }

        return list1;
    }

    private Long borrowBookBookId(BorrowBook borrowBook) {
        if ( borrowBook == null ) {
            return null;
        }
        Book book = borrowBook.getBook();
        if ( book == null ) {
            return null;
        }
        Long id = book.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
