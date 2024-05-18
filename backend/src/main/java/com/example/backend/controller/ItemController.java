package com.example.backend.controller;

import com.example.backend.entity.Item;
import com.example.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemrepository;

    @GetMapping("/api/items")
    public List<Item> getItems(){
        List<Item> items = itemrepository.findAll();
        return items;
    }
}
