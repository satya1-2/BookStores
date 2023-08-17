package com.example.bookstores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoresApplication.class, args);
        System.out.println("BookStore Application is Starting...");
    }

}
