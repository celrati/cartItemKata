package org.example.repository;

import org.example.model.Cart;
import org.example.model.CartItem;

import org.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndItem(Cart cart, Item item);
}
