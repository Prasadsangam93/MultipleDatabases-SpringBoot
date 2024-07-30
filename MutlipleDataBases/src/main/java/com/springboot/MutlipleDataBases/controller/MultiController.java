package com.springboot.MutlipleDataBases.controller;

import com.springboot.MutlipleDataBases.oracleentity.Book;
import com.springboot.MutlipleDataBases.oraclerepository.BookRepository;
import com.springboot.MutlipleDataBases.mysqlentity.User;
import com.springboot.MutlipleDataBases.mysqlrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SpringBootApplication
@RestController


public class MultiController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addData")
    public String addData2DB() {
        userRepository.saveAll(Stream.of(new User(101, "abhi"), new User(102, "virat")).collect(Collectors.toList()));
        bookRepository.saveAll(Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
        return "Data Added Successfully";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

}

