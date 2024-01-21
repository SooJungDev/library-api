package com.example.libraryapi.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.libraryapi.common.exception.EntityNotFoundException;
import com.example.libraryapi.customer.entity.Customer;
import com.example.libraryapi.customer.respository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("회원정보가 조회되지 않습니다."));
    }
}
