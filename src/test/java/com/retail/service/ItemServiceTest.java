package com.retail.service;
import com.retail.domain.Customer;
import com.retail.domain.Item;
import com.retail.domain.types.CustomerTypes;
import com.retail.domain.types.ItemTypes;
import com.retail.model.ItemDTO;
import com.retail.repository.ItemRepo;
import org.junit.Test;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    @Mock
    private ItemRepo itemsRepository;

    @InjectMocks
    private ItemService itemsService;

    @Test
    public void findAll() {
        List<Item> itemList = new ArrayList<>(2);
        itemList.add(new Item("Test 1", BigDecimal.TEN, ItemTypes.GROCERIES));
        itemList.add(new Item("Test 2", BigDecimal.ONE, ItemTypes.OTHERS));
        when(itemsRepository.findAll()).thenReturn(itemList);
        List<ItemDTO> listItems = itemsService.findAll();

    }

}
