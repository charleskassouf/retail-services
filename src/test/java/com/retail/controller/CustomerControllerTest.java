package com.retail.controller;

import com.retail.model.CustomerDTO;
import com.retail.model.ItemDTO;
import com.retail.service.CustomerService;
import com.retail.repository.ItemRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.core.Is.is;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private ItemRepo itemRepo;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(new CustomerController(customerService, itemRepo))
                .alwaysExpect(status().isOk()).build();
    }

    @Test
    public void checkCustomerDiscountsBeforeCheckout() throws Exception {
        when(customerService.calculateDiscountByUser(anyLong(), anyList())).thenReturn(BigDecimal.valueOf(30));
        this.mockMvc.perform(get("/customer/discount/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", is(30)));
    }
}
