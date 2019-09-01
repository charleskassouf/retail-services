package com.retail.service;

import com.retail.domain.Item;
import com.retail.model.CustomerDTO;
import com.retail.domain.Customer;
import com.retail.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService {
    private CustomerRepo customerRepo;

    private DiscountService discountService;
    @Autowired
    public CustomerService(CustomerRepo customerRepo, DiscountService discountService) {
        this.customerRepo = customerRepo;
        this.discountService = discountService;
    }

    public List<CustomerDTO> findAll() {
        return customerRepo.findAll()
                .stream()
                .map(item -> new CustomerDTO( item.getId(), item.getFirstName(), item.getLastName(), item.getCreatedDate(), item.getCustomerType()))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateDiscountByUser(long id, List<Item> items) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (!customer.isPresent()) {
            return BigDecimal.ZERO;
        }
        return discountService.userDiscount(customer.get(), items);
    }
}
