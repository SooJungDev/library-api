package com.example.libraryapi.borrow.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.example.libraryapi.borrow.dto.BorrowBookResponseDto;
import com.example.libraryapi.borrow.dto.BorrowResponseDto;
import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BorrowMapper {


    @Mappings({
            @Mapping(expression = "java(borrow.getStatus().name())", target = "status"),
            @Mapping(expression = "java(borrow.getStatus().getDescription())", target = "statusDescription"),
    })
    BorrowResponseDto of(Borrow borrow);

    @Mappings({
            @Mapping(expression = "java(borrowBook.getStatus().name())", target = "status"),
            @Mapping(expression = "java(borrowBook.getStatus().getDescription())", target = "statusDescription"),
            @Mapping(target = "bookId", source = "book.id"),
    })
    BorrowBookResponseDto of(BorrowBook borrowBook);
}
