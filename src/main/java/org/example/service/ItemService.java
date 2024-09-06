package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.Item;
import org.example.repository.CartItemRepository;
import org.example.repository.CartRepository;
import org.example.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    public void addProductToCart(Long cartId, Long itemId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalCart.isPresent() && optionalItem.isPresent()) {
            Cart cart = optionalCart.get();
            Item item = optionalItem.get();

            CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item);

            if (cartItem == null) {
                cartItem = new CartItem(cart, item, quantity);
                cart.addCartItem(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }

            cartItemRepository.save(cartItem);
        }
    }

    public void removeProductFromCart(Long cartId, Long itemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalCart.isPresent() && optionalItem.isPresent()) {
            Cart cart = optionalCart.get();
            Item item = optionalItem.get();

            CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item);

            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
            }
        }
    }

    @Transactional(readOnly = true)
    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
