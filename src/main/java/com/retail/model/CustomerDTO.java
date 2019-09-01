package com.retail.model;

import lombok.AllArgsConstructor;
import com.retail.domain.types.CustomerTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime createdDate;
    private CustomerTypes customerType;
}
