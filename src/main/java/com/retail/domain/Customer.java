package com.retail.domain;

import com.retail.domain.types.CustomerTypes;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="CUSTOMER")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerTypes customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PurchasedItem> purchasedItems;


    @Autowired
    public Customer(String lastName, LocalDateTime createdDate, CustomerTypes customerType) {
//        this.id = id;
//        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.customerType = customerType;
    }
}











