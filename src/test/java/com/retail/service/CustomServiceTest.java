package com.retail.service;
import com.retail.domain.Customer;
import com.retail.domain.Item;
import com.retail.domain.types.CustomerTypes;
import com.retail.domain.types.ItemTypes;
import com.retail.model.CustomerDTO;
import com.retail.model.ItemDTO;
import com.retail.repository.CustomerRepo;
import com.retail.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomServiceTest {
    @Mock
    private CustomerRepo customerRepository;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private CustomerService customerCustomerService;

    @Test
    public void discountsByCustomer() {
        long customerId = 1L;
        List<Item> customers  = new ArrayList<>(2);
        customers.add(new Item("test2", BigDecimal.valueOf(123), ItemTypes.GROCERIES));
        customers.add(new Item("test1", BigDecimal.valueOf(33.66), ItemTypes.OTHERS));
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        customerCustomerService.calculateDiscountByUser(customerId, customers);
        verify(customerRepository, times(1)).findById(customerId);
    }


    @Test
    public void findAll() {
        LocalDateTime today = LocalDateTime.now();
        List<Customer> customerList = new ArrayList<>(2);
        customerList.add(new Customer("kassouf", today, CustomerTypes.EMPLOYEE));
        when(customerRepository.findAll()).thenReturn(customerList);
        List<CustomerDTO> list= customerCustomerService.findAll();
    }
}
