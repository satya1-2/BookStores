package com.example.bookstores.model;

import com.example.bookstores.dto.BookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Book_Table")

public class Book {
    @Id
    @GeneratedValue
        private int bookId;
        private String bookName;
        private String bookAuthor;
        private String bookDescription;
        private String bookLogo;
        private long bookPrice;
        private int bookQuantity;
    public Book(BookDTO bookDTO) {
        this.updateBook(bookDTO);
    }

    public void updateBook(BookDTO bookDTO) {
        this.bookName=bookDTO.getBookName();
           this.bookAuthor= bookDTO.getBookAuthor();
           this.bookDescription=     bookDTO.getBookDescription();
            this.bookLogo=    bookDTO.getBookLogo();
             this.bookPrice=  bookDTO.getBookPrice();
             this.bookQuantity  = bookDTO.getBookQuantity();
    }
}
