package com.example.bookstores.dto;

import com.example.bookstores.model.Book;
import com.example.bookstores.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO
{
    private LocalDate orderDate = LocalDate.now();
    private long price;
    private long quantity;
    private String address;
    private long user;
    private int book;

}
