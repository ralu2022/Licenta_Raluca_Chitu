package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.Order;
import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.UserRepository;
import com.example.Luana_Nature.service.EmailService;
import com.example.Luana_Nature.service.OrderService;
import com.example.Luana_Nature.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/addorder")
    public String addOrder(@RequestParam String productName,
                           @RequestParam int orderedQuantity,
                           @RequestParam int pricePerItem,
                           @RequestParam double totalPrice,
                           @RequestParam LocalDate orderDate,
                           @RequestParam String status) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilizatorul nu a fost găsit!");
        }

    orderService.addOrder(productName, orderedQuantity, pricePerItem, totalPrice, orderDate,
            status, user.getUserId());

    return "redirect:/store";
    }
}



