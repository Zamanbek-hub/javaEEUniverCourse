package com.example.demo.entities.helpers;

import com.example.demo.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBasket {
    private Item item;
    private int count;
}
