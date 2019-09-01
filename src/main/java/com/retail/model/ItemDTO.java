package com.retail.model;

import com.retail.domain.types.ItemTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO implements Serializable {
    private Long id;
    private String itemName;
    private BigDecimal itemPrice;
    private ItemTypes itemType;

}
