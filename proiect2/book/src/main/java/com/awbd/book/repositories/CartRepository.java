package com.awbd.book.repositories;

import com.awbd.book.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
//    List<Cart> findByUser(User user);
}
