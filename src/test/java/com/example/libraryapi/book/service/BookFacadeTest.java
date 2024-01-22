package com.example.libraryapi.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
@ActiveProfiles("test")
class BookFacadeTest {

    @Autowired
    private BookFacade bookFacade;

}
