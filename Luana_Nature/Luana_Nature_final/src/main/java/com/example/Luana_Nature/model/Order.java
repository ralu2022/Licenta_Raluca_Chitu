package com.example.Luana_Nature.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private String productName;
    private int orderedQuantity;
    private int pricePerItem;
    private double totalPrice;
    private LocalDate orderDate;
    private String status ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User orderUser;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;


}