package com.example.bookstores.repo;

import com.example.bookstores.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepo extends JpaRepository<Book,Integer> {

}
