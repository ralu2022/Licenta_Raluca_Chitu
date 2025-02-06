/*
 * Controller used to handle CRUD (create, read, update, delete) operations upon Reservation entity.
 *
 * For the read operations there two @GetMapping end points declared as the admin has also the possibility to modify or
 * delete a reservation.
 *
 * */


package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.Reservation;
import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.ReservationRepository;
import com.example.Luana_Nature.service.EmailService;
import com.example.Luana_Nature.service.ReservationService;
import com.example.Luana_Nature.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor

public class ReservationController {

    private final ReservationService reservationService;
    private final EmailService emailService;
    private final ReservationRepository reservationRepository;
    private final UserService userService;


    /* Vizualizare rezervări - admin */

    @GetMapping("/reservationsadmin")
    public String allReservationsAdmin(Model model) {
        model.addAttribute("reservationsadmin", reservationService.getAllReservations());
        return "reservationsadmin";
    }

    /* Adăugare rezervare */

    @GetMapping("/reservation")
    public String reservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservationformuser";
    }

    @PostMapping("/addReservation")
    public String addReservation(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String company,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                                 @RequestParam int numberOfPersons,
                                 @RequestParam String accommodationType,
                                 @RequestParam String cateringType,
                                 @RequestParam String cateringMentions,
                                 @RequestParam(defaultValue = " ") String drinkType,
                                 @RequestParam(defaultValue = " ") String activity,
                                 @RequestParam(defaultValue = " ") String period,
                                 @RequestParam String message) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Utilizatorul nu a fost găsit!");
        }

        Reservation reservation = reservationService.addReservation(name, email, phone, company, arrivalDate, departureDate, numberOfPersons,
                accommodationType, cateringType, cateringMentions, drinkType, activity, period, message, user);

        String subject = "Rezervare nouă";
        String text = "Rezervarea dvs. cu numărul " + reservation.getReservationId() +  " a fost creată!";
        emailService.sendEmail(email, subject,text,name);

        return "redirect:/mainpageuser";
    }

    /* Modificare rezervare */

    @GetMapping("/updateReservation/{reservationId}")
    public String reservationFormUpdated(@PathVariable Long reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation != null) {
            model.addAttribute("reservationadmin", reservation);
            return "reservationformupdate";
        } else {
            return "redirect:/reservations/reservationsadmin";
        }

    }

    @PutMapping("/updateReservation/{reservationId}")
    @SuppressWarnings("all")
    public String updateReservation(@PathVariable Long reservationId,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String phone,
                                    @RequestParam String company,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                                    @RequestParam int numberOfPersons,
                                    @RequestParam String accommodationType,
                                    @RequestParam String cateringType,
                                    @RequestParam String cateringMentions,
                                    @RequestParam String drinkType,
                                    @RequestParam String activity,
                                    @RequestParam String period,
                                    @RequestParam String message) {
        Reservation currentReservation = reservationService.getReservationById(reservationId);

        if (currentReservation != null) {
            currentReservation.setName(name);
            currentReservation.setEmail(email);
            currentReservation.setPhone(phone);
            currentReservation.setCompany(company);
            currentReservation.setArrivalDate(arrivalDate);
            currentReservation.setDepartureDate(departureDate);
            currentReservation.setNumberOfPersons(numberOfPersons);
            currentReservation.setAccommodationType(accommodationType);
            currentReservation.setCateringType(cateringType);
            currentReservation.setCateringMentions(cateringMentions);
            currentReservation.setDrinkType(drinkType);
            currentReservation.setActivity(activity);
            currentReservation.setPeriod(period);
            currentReservation.setMessage(message);

            reservationService.updateReservation(currentReservation);

            String subject = "Rezervare actualizată";
            String text = "Rezervarea dvs. a fost actualizată!";
            emailService.sendEmail(email,subject,text, name);
        }
        return "redirect:/reservations/reservationsadmin";
    }


    /* Ștergere rezervare */

    @DeleteMapping("/deleteReservation")
    public String deleteReservation(@RequestParam Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation != null) {
            reservationService.deleteReservation(reservationId);
        }
        return "redirect:/reservations/reservationsadmin";
    }

    /* Metodă de numărare a rezervărilor în funcție de tipul cazării */

    @GetMapping("/api/count-by-type")
    @ResponseBody
    public List<Map<String, Object>> getCountByType() {
        return reservationService.getReservationCountByType();
    }

    @GetMapping("/chart")
    public String showReservationChart(Model model) {
        List<Map<String, Object>> reservationData = reservationService.getReservationCountByType();
        model.addAttribute("reservationData", reservationData);
        return "redirect:/mainpage";
    }


}
