package com.retail.controller;

import com.retail.model.ItemDTO;
import com.retail.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class ItemControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ItemService itemsService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(new ItemController(itemsService))
                .alwaysExpect(status().isOk()).build();
    }

    @Test
    public void getItems() throws Exception {
        List<ItemDTO> itemDtoList = new ArrayList<>(2);
        itemDtoList.add(new ItemDTO());
        itemDtoList.add(new ItemDTO());

        when(itemsService.findAll()).thenReturn(itemDtoList);
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
