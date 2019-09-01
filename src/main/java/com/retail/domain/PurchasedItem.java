package com.retail.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name = "PURCHASEDITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedItem {
    @Id
    @Setter(AccessLevel.PACKAGE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private BigDecimal bill;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "purchasedItem")
    private Set<Item> purchasedItems;
}
