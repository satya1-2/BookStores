package com.example.bookstores.model;
import com.example.bookstores.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "/order_table")

public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private int orderId;
    private LocalDate orderDate = LocalDate.now();
    private long price;
    private long quantity;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private boolean cancel;





    public void updateOrder(OrderDTO orderDTO) {
        this.setQuantity(orderDTO.getQuantity());
        this.setPrice (this.getQuantity() * this.getBook().getBookPrice());
        this.address = orderDTO.getAddress();
    }
    public Order(User user, Book book, OrderDTO orderDTO) {
        this.user = user;
        this.book = book;
        this.cancel = false; // Assuming cancel status should be set to false by default

        if (user != null && book != null) {
            this.updateOrder(orderDTO);
        }
    }
}


