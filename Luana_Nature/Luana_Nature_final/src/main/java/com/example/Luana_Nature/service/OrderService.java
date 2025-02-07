package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Order;
import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.OrderRepository;
import com.example.Luana_Nature.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public Order addOrder(String productName, int orderedQuantity, int pricePerItem, double totalPrice,
                         LocalDate orderDate, String status, Long userId) {

        User user = userRepository.findById(userId).get();
        Order order = new Order();
        order.setProductName(productName);
        order.setOrderedQuantity(orderedQuantity);
        order.setPricePerItem(pricePerItem);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(orderDate);
        order.setStatus(status);
        order.setOrderUser(user);

        return orderRepository.save(order);

    }
}
