package com.retail.service;

import com.retail.domain.Customer;
import com.retail.domain.Item;
import com.retail.domain.types.ItemTypes;
import com.retail.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountService {
    public static BigDecimal userDiscount(Customer customer, List<Item> items) {
        final BigDecimal totalPurchaseAmount = removeGroceries(items);
        if (totalPurchaseAmount.equals(BigDecimal.ZERO)) {
            return totalPurchaseAmount;
        }
        System.out.println(customer.getCustomerType());
            if (customer.getCustomerType().toString().equals("EMPLOYEE")) {
            return calculatePercentage(BigDecimal.valueOf(30), totalPurchaseAmount);
        }

        if (customer.getCustomerType().toString().equals("AFFILIATE")) {
            return calculatePercentage(BigDecimal.valueOf(10), totalPurchaseAmount);
        }

        if (customer.getCreatedDate().isBefore(LocalDateTime.now().minusYears(2))) {
            return calculatePercentage(BigDecimal.valueOf(5), totalPurchaseAmount);
        }

        if (totalPurchaseAmount.compareTo(BigDecimal.valueOf(100)) > 0) {
            return totalPurchaseAmount.divide(BigDecimal.valueOf(100), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(5));
        }
        return BigDecimal.ZERO;
    }

    private static BigDecimal removeGroceries(List<Item> purchasedItems) {
        return purchasedItems.stream()
                .filter(i -> i.getItemType() != ItemTypes.GROCERIES)
                .map(Item::getItemPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

    private static BigDecimal calculatePercentage(BigDecimal percentage, BigDecimal amount) {
        return amount.multiply(percentage).divide(BigDecimal.valueOf(100), 10, RoundingMode.FLOOR);
    }
}
