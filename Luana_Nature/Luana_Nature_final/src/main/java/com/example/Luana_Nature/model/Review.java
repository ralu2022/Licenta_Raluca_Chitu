/*
 * This class represents a review entity and is used to store details about reviews sent by authenticated users.
 *
 * <p> The class uses the Lombok library, so that redundant code is avoided. The get(), set() and constructor methods
 * are implemented automatically using annotations. Also, JPA annotations are used for mapping the class to the
 * database table named reviews and to handle relationship with entity User.
 * */


package com.example.Luana_Nature.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")

public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reviewUser;

}
