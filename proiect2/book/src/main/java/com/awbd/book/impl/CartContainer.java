package com.awbd.book.impl;

import com.awbd.book.DiscountServiceProxy;
import com.awbd.book.model.Book;
import com.awbd.book.model.Cart;
import com.awbd.book.model.CartItem;
import com.awbd.book.model.Discount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Slf4j
@Component
@Scope(scopeName = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class CartContainer implements Serializable {
    private Cart cart;
    private Map<Integer, CartItem> cartItems;

    public CartContainer() {
        cart = new Cart();
        cartItems = new HashMap<>();
    }

    @Autowired
    DiscountServiceProxy discountServiceProxy;

    public Cart getCart() {
        return cart;
    }

    public List<CartItem> getCartItems() {
        return new LinkedList<>(cartItems.values());
    }

    public void addItem(Book book) {
        int productId = Math.toIntExact(book.getId());
        CartItem tempItem = cartItems.get(productId);
        if (tempItem != null) {
            incrementQuantity(productId);
        } else {
            tempItem = new CartItem();
            tempItem.setCart(cart);
            tempItem.setBook(book);
            tempItem.setAmount(1);
            cartItems.put(productId, tempItem);
        }
    }

    public void removeItem(int productID) {

        CartItem tempItem = cartItems.get(productID);
        if (tempItem != null) {
            decrementQuantity(productID);
            if (cartItems.get(productID).getAmount() < 1) {
                cartItems.remove(productID);
            }
        }
    }

    public int getDiscountServiceProxy() {
        return discountServiceProxy.findDiscount().getStudent();
    }

    public int getTotalPrice() {
        int total = 0;
        List<CartItem> cartItemList = getCartItems();
        for (CartItem item : cartItemList) {
            total += getProductTotalPrice(Math.toIntExact(item.getBook().getId()));
//            Discount discount = discountServiceProxy.findDiscount();
//            log.info(Integer.toString(discount.getInstanceId()));
//            total = total - (total * discount.getStudent())/100;
//            book.setPrice(book.getPrice() - (book.getPrice() * discount.getStudent())/100);
        }
        return total;
    }

    public int getTotalDiscountPrice() {
        int total = 0;
        List<CartItem> cartItemList = getCartItems();
        for (CartItem item : cartItemList) {
            total += getProductDiscountTotalPrice(Math.toIntExact(item.getBook().getId()));
//            Discount discount = discountServiceProxy.findDiscount();
//            log.info(Integer.toString(discount.getInstanceId()));
//            total = total - (total * discount.getStudent())/100;
//            book.setPrice(book.getPrice() - (book.getPrice() * discount.getStudent())/100);
        }
        return total;
    }


    public void clear() {
        cartItems.clear();
    }

    public void incrementQuantity(int productId) {
        int amount = cartItems.get(productId).getAmount();
        cartItems.get(productId).setAmount(++amount);
    }

    public void decrementQuantity(int productId) {
        int amount = cartItems.get(productId).getAmount();
        cartItems.get(productId).setAmount(--amount);
    }


    public int getProductTotalPrice(int productID) {
        Discount discount = discountServiceProxy.findDiscount();
        log.info(Integer.toString(discount.getInstanceId()));
        int price = (int) cartItems.get(productID).getBook().getPrice();
//        int discountPrice = price - (price * discount.getStudent())/100;
        int amount = cartItems.get(productID).getAmount();
        return price * amount;
    }

    public int getProductDiscountTotalPrice(int productID) {
        Discount discount = discountServiceProxy.findDiscount();
        log.info(Integer.toString(discount.getInstanceId()));
        int price = (int) cartItems.get(productID).getBook().getPrice();
        int discountPrice = price - (price * discount.getStudent())/100;
        int amount = cartItems.get(productID).getAmount();
        return discountPrice * amount;
    }

    @Override
    public String toString() {
        return "{\"CartContainer\":{"
                + "\"cart\":" + cart
                + ", \"cartItems\":" + cartItems
                + "}}";
    }
}
