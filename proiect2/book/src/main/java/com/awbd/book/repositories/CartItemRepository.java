package com.awbd.book.repositories;

import com.awbd.book.model.Cart;
import com.awbd.book.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(Cart cart);
}
