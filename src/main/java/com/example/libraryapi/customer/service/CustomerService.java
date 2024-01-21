package com.example.libraryapi.customer.service;

import com.example.libraryapi.customer.entity.Customer;

public interface CustomerService {
    Customer findById(Long id);
}
