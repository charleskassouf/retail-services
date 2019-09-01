package com.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.retail.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import com.retail.model.ItemDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private  ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ItemDTO> get() {
        return itemService.findAll();
    }
}
