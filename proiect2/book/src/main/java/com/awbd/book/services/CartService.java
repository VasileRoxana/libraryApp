package com.awbd.book.services;


import com.awbd.book.impl.CartContainer;
import com.awbd.book.repositories.CartItemRepository;
import com.awbd.book.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartContainer cartContainer;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


//    @Transactional
//    public void submitOrderForPayment() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        User user = userService.findByUsername(name);
//
//        cartContainer.getCart().setUser(user);
//        cartRepository.save(cartContainer.getCart());
//        cartItemRepository.saveAll(cartContainer.getCartItems());
//        cartContainer.clear();
//    }
//
//    public List<OrderContainer> getOrders(){
//        User user = userService.getLoggedInUser();
//        List<Cart> cartList = cartRepository.findByUser(user);
//
//        List<OrderContainer> result = new LinkedList<>();
//        for (Cart cart : cartList){
//            List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
//            result.add(new OrderContainer(cart, cartItemList));
//        }
//        return result;
//    }
}
