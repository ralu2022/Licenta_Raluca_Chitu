/**
 * Service class for managing reservations. This class interacts with the ReservationRepository to perform
 * CRUD (Create, Read, Update, Delete) operations for reservations.
 * <p>
 * The ReservationService class provides methods to add, update, delete, and retrieve reservations. It also
 * offers a method to get the count of reservations grouped by type.
 * </p>
 */

package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Reservation;
import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.ReservationRepository;
import com.example.Luana_Nature.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    /* READ */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    /* CREATE */
    @SuppressWarnings("all")
    public Reservation addReservation(String name, String email, String phone, String company, LocalDate arrivalDate,
                               LocalDate departureDate, int numberOfPersons, String accommodationType, String cateringType,
                               String cateringMentions, String drinkType, String activity, String period, String message, User user) {

        Reservation reservation = new Reservation();

        reservation.setName(name);
        reservation.setEmail(email);
        reservation.setPhone(phone);
        reservation.setCompany(company);
        reservation.setArrivalDate(arrivalDate);
        reservation.setDepartureDate(departureDate);
        reservation.setNumberOfPersons(numberOfPersons);
        reservation.setAccommodationType(accommodationType);
        reservation.setCateringType(cateringType);
        reservation.setCateringMentions(cateringMentions);
        reservation.setDrinkType(drinkType);
        reservation.setActivity(activity);
        reservation.setPeriod(period);
        reservation.setMessage(message);
        reservation.setReservationUser(user);

        return reservationRepository.save(reservation);
    }

    /* UPDATE */
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /* DELETE */
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalArgumentException(
                "Rezervarea nu există!"));
        reservationRepository.delete(reservation);
    }


    public List<Map<String, Object>> getReservationCountByType() {
        return reservationRepository.countReservationsByType();
    }


    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }



}







