package com.retail.service;
import com.retail.domain.Customer;
import com.retail.domain.Item;
import com.retail.domain.types.CustomerTypes;
import com.retail.domain.types.ItemTypes;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @Test
    public void discountsEmployee() {
        List<Item> customers  = new ArrayList<>(2);
        LocalDateTime today = LocalDateTime.now();
        customers.add(new Item("test2", BigDecimal.valueOf(123), ItemTypes.GROCERIES));
        customers.add(new Item("test1", BigDecimal.valueOf(33.66), ItemTypes.OTHERS));
        DiscountService.userDiscount(new Customer("kassouf", today, CustomerTypes.EMPLOYEE ), customers);
    }

    @Test
    public void discountsAffiliate() {
        List<Item> customers  = new ArrayList<>(2);
        LocalDateTime today = LocalDateTime.now();
        customers.add(new Item("test2", BigDecimal.valueOf(123), ItemTypes.GROCERIES));
        customers.add(new Item("test1", BigDecimal.valueOf(33.66), ItemTypes.OTHERS));
        DiscountService.userDiscount(new Customer("kassouf", today , CustomerTypes.AFFILIATE ), customers);
    }

    @Test
    public void discountsCustomer() {
        List<Item> customers  = new ArrayList<>(2);
        LocalDateTime today = LocalDateTime.now();
        customers.add(new Item("test2", BigDecimal.valueOf(123), ItemTypes.GROCERIES));
        customers.add(new Item("test1", BigDecimal.valueOf(33.66), ItemTypes.OTHERS));
        DiscountService.userDiscount(new Customer("kassouf", today, CustomerTypes.CUSTOMER ), customers);
    }
}
