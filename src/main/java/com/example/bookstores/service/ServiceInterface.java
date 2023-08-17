package com.example.bookstores.service;

import com.example.bookstores.dto.CartDTO;
import com.example.bookstores.model.Cart;
import com.example.bookstores.model.User;
import com.example.bookstores.responseDto.ResponseDTO;

import java.util.List;

public interface ServiceInterface {

    ResponseDTO addToCart(CartDTO cartDTO);

    User getCartById(long cartId);

    void deleteCartById(long cartId);

    void removeCartItemsByUser(String token);

    Cart updateCartItemQuantity(String token, long cartId, long quantity);

    List<Cart> getAllCartItemsForUser(String token);

    List<Cart> getAllCarts();

    // You can add more common methods here if needed
}

