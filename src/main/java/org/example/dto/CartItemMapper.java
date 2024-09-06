package org.example.dto;

import org.example.model.CartItem;

import java.util.Set;
import java.util.stream.Collectors;

public class CartItemMapper {

    public static CartItemDTO cartItemToCartItemDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .itemId(cartItem.getItem().getId())
                .itemName(cartItem.getItem().getName())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public static Set<CartItemDTO> cartItemsToCartItemDTOs(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItemMapper::cartItemToCartItemDTO)
                .collect(Collectors.toSet());
    }
}
