package com.example.bookstores.service;

import com.example.bookstores.dto.OrderDTO;
import com.example.bookstores.model.Book;
import com.example.bookstores.model.Order;
import com.example.bookstores.model.User;
import com.example.bookstores.repo.OrderRepo;
import com.example.bookstores.responseDto.ResponseDTO;
import com.example.bookstores.util.EmailService;
import com.example.bookstores.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private EmailService emailService;

    public ResponseDTO placeOrder(String token, OrderDTO orderDTO) {
        User user = getUserFromToken(token);
        if (user != null) {
            Book book = bookService.getBookById(orderDTO.getBook());
            if (book != null && book.getBookQuantity() >= orderDTO.getQuantity()) {
                Order newOrder = new Order(user, book, orderDTO);
                orderRepo.save(newOrder);
                // Update book quantity and other necessary operations
                ResponseDTO responseDTO = new ResponseDTO("Order placed successfully", newOrder);
                emailService.sendEmail(newOrder.getUser().getEmailId(),"order placed","adderss"+orderDTO.getAddress());
                return responseDTO;
            } else {
                return new ResponseDTO("Invalid book or quantity", null);
            }
        } else {
            return new ResponseDTO("Unauthorized", null);
        }

    }
    public boolean cancelOrder(long userId, int orderId) {
        Order order = orderRepo.findByUserIdAndOrderId(userId, orderId);
        if (order != null) {
            if (!order.isCancel()) {
                order.setCancel(true);
                orderRepo.save(order);
                return true;
            }
        }
        return false;
    }

    private User getUserFromToken(String token) {
        try {
            long UserId = jwtToken.decodeToken(token);
            return userService.getUserById(UserId);
        } catch (Exception e) {
            return null; // Token decoding or user retrieval failed
        }
    }


    public List<Order> getAllOrdersForUser(long userId) {
        return orderRepo.findByUserIdAndCancel(userId, false);
    }

    public List<Order> getAllOrders(boolean cancel) {
        return orderRepo.findByCancel(cancel);
    }

}

