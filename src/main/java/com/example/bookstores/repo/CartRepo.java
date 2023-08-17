package com.example.bookstores.repo;

import com.example.bookstores.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(value = "select * from cart_table where user_id = ?", nativeQuery = true)
    List<Cart> findByUserId( long userId);

    @Query(value = "SELECT * FROM `/cart_table`  WHERE cart_id = :cartId AND user_id = :Id", nativeQuery = true)
    Cart findByCartIdAndUserId(@Param("cartId") long cartId, @Param("Id") long Id);
}
