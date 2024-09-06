package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.CartDTO;
import org.example.dto.CartMapper;
import org.example.dto.ItemDTO;
import org.example.dto.ItemMapper;
import org.example.model.Cart;
import org.example.model.Item;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager/cart")
@RequiredArgsConstructor
public class CartController {


    private final ItemService itemService;  // Assuming the methods are in CartService

    @PostMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<String> addItemToCart(
            @PathVariable Long cartId, @PathVariable Long itemId, @RequestParam int quantity) {
        try {
            itemService.addProductToCart(cartId, itemId, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add item to cart.");
        }
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<String> removeItemFromCart(
            @PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            itemService.removeProductFromCart(cartId, itemId);
            return ResponseEntity.status(HttpStatus.OK).body("Item removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove item from cart.");
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> viewCart(@PathVariable Long cartId) {
        try {
            Cart cart = itemService.viewCart(cartId);
            CartDTO cartDTO = CartMapper.cartToCartDTO(cart);
            return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
