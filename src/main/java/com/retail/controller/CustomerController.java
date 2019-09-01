package com.retail.controller;

import com.retail.domain.Item;
import com.retail.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.retail.model.PurchaseDTO;
import org.springframework.http.MediaType;
import com.retail.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.retail.repository.ItemRepo;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final ItemRepo itemRepo;

    @Autowired
    public CustomerController(CustomerService customerService, ItemRepo itemRepo) {
        this.customerService = customerService;
        this.itemRepo = itemRepo;
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CustomerDTO> get() {
        return customerService.findAll();
    }

    @GetMapping(value = "/customer/discount/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BigDecimal getUserDiscount(@PathVariable long id) {
        return customerService.calculateDiscountByUser(id, getAllItems());
    }

    private List<Item> getAllItems() {
        return itemRepo.findAll();
    }
}
