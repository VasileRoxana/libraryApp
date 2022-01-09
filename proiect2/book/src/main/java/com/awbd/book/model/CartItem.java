package com.awbd.book.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "cart_item")
public class CartItem implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shopCartId", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Book book;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Transient
    public double getTotalPrice(){
        return amount * book.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        if (!id.equals(cartItem.id)) return false;
        if (!cart.equals(cartItem.cart)) return false;
        if (!book.equals(cartItem.book)) return false;
        return amount.equals(cartItem.amount);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cart.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{\"CartItem\":{"
                + "\"id\":\"" + id + "\""
                + ", \"cart\":" + cart
                + ", \"product\":" + book
                + ", \"amount\":\"" + amount + "\""
                + "}}";
    }
}
