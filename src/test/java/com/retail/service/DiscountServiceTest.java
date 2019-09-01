package com.retail.service;
import com.retail.domain.Customer;
import com.retail.domain.Item;
import com.retail.domain.types.CustomerTypes;
import com.retail.domain.types.ItemTypes;
import com.retail.repository.CustomerRepo;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

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
        customers.add(new Item("test2", BigDecimal.valueOf(100), ItemTypes.GROCERIES));
        customers.add(new Item("test1", BigDecimal.valueOf(50), ItemTypes.OTHERS));
        DiscountService discountService = new DiscountService();
        BigDecimal result =  discountService.userDiscount(new Customer("kassouf", today, CustomerTypes.AFFILIATE ), customers);
        assertEquals(BigDecimal.valueOf(5), result);
    }
}
