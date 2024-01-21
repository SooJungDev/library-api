package com.example.libraryapi.book;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.example.libraryapi.book.dto.BookRequestDto;
import com.example.libraryapi.book.dto.BookResponseDto;
import com.example.libraryapi.book.entity.Book;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BookMapper {


    Book of(BookRequestDto bookDto);

    @Mappings({
            @Mapping(expression = "java(book.getStatus().name())", target = "bookStatus"),
            @Mapping(expression = "java(book.getStatus().getDescription())", target = "bookStatusDescription"),
    })
    BookResponseDto of(Book book);
}
