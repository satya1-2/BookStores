package com.example.bookstores.repo;

import com.example.bookstores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository

public interface UserRepo extends JpaRepository<User,Long> {
    User getUserByEmailId(String emailId);


    User findByEmailId(String emailId);
}
