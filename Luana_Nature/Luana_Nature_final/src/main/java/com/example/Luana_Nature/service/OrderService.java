package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Order;
import com.example.Luana_Nature.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order addOrder(String productName, int orderedQuantity, int pricePerItem, double totalPrice,
                         LocalDate orderDate, String status) {

        Order order = new Order();
        order.setProductName(productName);
        order.setOrderedQuantity(orderedQuantity);
        order.setPricePerItem(pricePerItem);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(orderDate);
        order.setStatus(status);

        return orderRepository.save(order);

    }
}
