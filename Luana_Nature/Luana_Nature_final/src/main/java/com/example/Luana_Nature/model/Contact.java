/*
 * This class represents a contact entity and is used to store details about messages sent by authenticated users or
 * visitors.
 *
 * <p> The class uses the Lombok library, so that redundant code is avoided. The get(), set() and constructor methods
 * are implemented automatically using annotations. Also, JPA annotations are used for mapping the class to the
 * database table named contact and to handle relationship with entity User.
 * */

package com.example.Luana_Nature.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")

public class Contact {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactMessageId;
    private String name;
    private String email;
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User contactUser;

}
