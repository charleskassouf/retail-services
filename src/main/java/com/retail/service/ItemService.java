package com.retail.service;

import com.retail.model.ItemDTO;
import com.retail.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private ItemRepo itemRepo;

    @Autowired
    public ItemService (ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }
    public List<ItemDTO> findAll() {
        return itemRepo.findAll()
                .stream()
                .map(item -> new ItemDTO(item.getId(), item.getItemName(), item.getItemPrice(), item.getItemType()))
                .collect(Collectors.toList());
    }
}
