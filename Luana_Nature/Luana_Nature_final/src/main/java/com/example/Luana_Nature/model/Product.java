/*
 * This class represents a product entity and is used to store details about products.
 *
 * <p> The class uses the Lombok library, so that redundant code is avoided. The get(), set() and constructor methods
 * are implemented automatically using annotations. Also, JPA annotations are used for mapping the class to the
 * database table products and to handle relationship with entities User and Order.
 * */

package com.example.Luana_Nature.model;


import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;
    private int price;
    private int stock;
    private String description;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User productUser;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String imageUrl;
}