package org.example.dto;

import org.example.model.Item;

import java.util.List;

public class ItemMapper {

    public static ItemDTO itemToItemDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .price(item.getPrice())
                //.cart_id(item.getCart().getId())
                .name(item.getName())
                .build();
    }

    public static List<ItemDTO> itemsToItemsDTOS(List<Item> items) {
        return items.stream()
                .map(ItemMapper::itemToItemDTO)
                .toList();
    }
}
