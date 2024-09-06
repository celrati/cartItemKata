package org.example.dto;

import org.example.model.Cart;

import java.util.Set;

public class CartMapper {

    public static CartDTO cartToCartDTO(Cart cart) {
        Set<CartItemDTO> cartItemDTOs = CartItemMapper.cartItemsToCartItemDTOs(cart.getCartItems());
        return CartDTO.builder()
                .id(cart.getId())
                .items(cartItemDTOs)
                .build();
    }
}
