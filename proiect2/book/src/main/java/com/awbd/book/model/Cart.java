package com.awbd.book.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
//    private User user;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (!id.equals(cart.id)) return false;
//        if (!user.equals(cart.user)) return false;
        return Objects.equals(deliveryAddress, cart.deliveryAddress);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
//        result = 31 * result + user.hashCode();
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"Cart\":{"
                + "\"id\":\"" + id + "\""
//                + ", \"user\":" + user
                + ", \"deliveryAddress\":\"" + deliveryAddress + "\""
                + "}}";
    }
}
