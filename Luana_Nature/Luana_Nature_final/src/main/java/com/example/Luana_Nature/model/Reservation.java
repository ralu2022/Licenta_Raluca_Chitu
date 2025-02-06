/*
 * This class represents a reservation entity and is used to store details about reservations sent by authenticated users.
 *
 * <p> The class uses the Lombok library, so that redundant code is avoided. The get(), set() and constructor methods
 * are implemented automatically using annotations. Also, JPA annotations are used for mapping the class to the
 * database table named reservations and to handle relationship with entity User.
 * */


package com.example.Luana_Nature.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")


public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private String name;
    private String email;
    private String phone;
    private String company;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int numberOfPersons;
    private String accommodationType;
    private String cateringType;
    private String cateringMentions;
    private String drinkType;
    private String message;
    private String activity;
    private String period;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reservationUser;



}
