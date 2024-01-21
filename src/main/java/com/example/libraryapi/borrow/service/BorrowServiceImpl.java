package com.example.libraryapi.borrow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.borrow.entity.BorrowBook;
import com.example.libraryapi.borrow.repository.repository.BorrowRepository;
import com.example.libraryapi.common.exception.EntityNotFoundException;
import com.example.libraryapi.customer.entity.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Borrow borrow(List<BorrowBook> borrowBooks, Customer customer) {
        final Borrow borrow = Borrow.createBorrow(borrowBooks, customer);
        borrowRepository.save(borrow);
        return borrow;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Borrow returnBook(Borrow borrow, List<BorrowBook> returnBooks) {
        borrow.returnBorrowBook(returnBooks);
        return borrow;
    }

    @Override
    public Borrow findByIdAndCustomerId(Long id, Long customerId) {
        return borrowRepository.findByIdAndCustomerId(id, customerId).
                               orElseThrow(() -> new EntityNotFoundException(String.format("해당 Id %d 대여 정보가 존재하지 않습니다.", id)));
    }

}
