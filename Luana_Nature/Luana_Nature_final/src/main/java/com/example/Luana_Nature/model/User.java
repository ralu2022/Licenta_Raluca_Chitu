/*
 * This class represents a user entity and is used to store details about users.
 *
 * <p> The class uses the Lombok library, so that redundant code is avoided. The get(), set() and constructor methods
 * are implemented automatically using annotations. Also, JPA annotations are used for mapping the class to the
 * database table named users and to handle relationship with entities Reservation, Order, Review, Product and Contact.
 * */

package com.example.Luana_Nature.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "reservationUser")
    private List<Reservation> reservationList;

    @OneToMany(mappedBy = "reviewUser")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "contactUser")
    private List<Contact> contactList;

    @OneToMany(mappedBy = "productUser")
    private List<Product> productList;

    @OneToMany(mappedBy = "orderUser")
    private List<Order> orders;


}