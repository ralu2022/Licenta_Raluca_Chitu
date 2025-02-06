package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.Order;
import com.example.Luana_Nature.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/addorder")
    public String addOrder(@RequestParam String productName,
                           @RequestParam int orderedQuantity,
                           @RequestParam int pricePerItem,
                           @RequestParam double totalPrice,
                           @RequestParam LocalDate orderDate,
                           @RequestParam String status) {

    Order order = orderService.addOrder(productName, orderedQuantity, pricePerItem, totalPrice, orderDate, status);

    return "redirect:/store";
    }
}


    /*
    @PostMapping("/addorder")
    public ResponseEntity<String> submitOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("OK!");
    }
} Comandă unică...*/



