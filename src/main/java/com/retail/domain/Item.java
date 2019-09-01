package com.retail.domain;

import com.retail.domain.types.ItemTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="ITEMS")
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @Setter(AccessLevel.PACKAGE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemTypes itemType;

    @ManyToOne
    @JoinColumn
    private PurchasedItem purchasedItem;

    public Item(String itemName, BigDecimal itemPrice, ItemTypes itemType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }
}
