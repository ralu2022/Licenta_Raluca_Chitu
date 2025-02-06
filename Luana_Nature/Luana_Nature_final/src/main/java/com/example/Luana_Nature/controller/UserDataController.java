/*
 * Controller used to handle CRUD (create, read, update, delete) operations upon User entity.
 *
 *
 * */


package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.UserRepository;
import com.example.Luana_Nature.service.EmailService;

import com.example.Luana_Nature.service.OrderService;
import com.example.Luana_Nature.service.ReservationService;
import com.example.Luana_Nature.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /* Vizualizare utilizatori pentru admin */

    @GetMapping("/usersadmin")
    public String allUsersAdmin(Model model) {
        model.addAttribute("usersadmin", userRepository.findAll());
        return "usersadmin";
    }


    /* Înregistrare user */

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/adduser")
    public String addUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            String role, Model model) {

        try {
            userService.addUser(name, email, username, password, role);

            String subject = "User nou";
            String text = "Utilizator nou creat:\nName: " + name + "\nEmail: " + email + "\nUsername: " + username;
            emailService.sendEmail(email, subject, text, name);

            return "redirect:/index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
            return "register";
        }
    }

        /* Logarea unui user */

        @GetMapping("/login")
        public String logInForm (Model model){
            model.addAttribute("user", new User());
            return "login";
        }

        @PostMapping("/login")
        public String signIn (@RequestParam String username,
                @RequestParam String password,
                Model model){
            try {

                if (!userService.isValidUser(username, password)) {
                    model.addAttribute("error", "Parola este greșită!");
                    return "index";
                }
                return "redirect:/mainpageuser";

            } catch (Exception e) {
                model.addAttribute("error", "Utilizatorul nu există!");
                return "index";
            }
        }

        @GetMapping("/loginadmin")
        public String logInFormAdmin (Model model){
            model.addAttribute("user", new User());
            return "mainpageadmin";
        }

        @PostMapping("/loginadmin")
        public String signInAdmin (@RequestParam String username,
                @RequestParam String password,
                Model model){
            if ("admin".equals(username) && "admin".equals(password)) {
                return "mainpageadmin";
            } else {
                model.addAttribute("error", "Credențiale incorecte!");
                return "loginadmin";
            }
        }


        /* Actualizare date utilizator */

        @GetMapping("/updateUserForm")
        public String updateUserForm (Model model, Principal principal){
            String loggedUsername = principal.getName();

            User user = userRepository.findByUsername(loggedUsername);
            if (user != null) {
                model.addAttribute("userUpdate", user);
                return "userupdateform";
            }
            return "redirect:/mainpageuser";
        }

        @PutMapping("/updateUser")
        public String updateUser (Principal principal,
                @RequestParam String name,
                @RequestParam String email,
                @RequestParam String username,
                @RequestParam String password){

            String loggedUsername = principal.getName();
            User currentUser = userRepository.findByUsername(loggedUsername);

            if (currentUser != null) {
                currentUser.setName(name);
                currentUser.setEmail(email);
                currentUser.setUsername(username);
                currentUser.setPassword(passwordEncoder.encode(password));
                userService.updateUser(currentUser);

                String subject = "Utilizator actualizat";
                String text = "Datele dvs. au fost actualizate!";
                emailService.sendEmail(email, subject, text, name);
            }
            return "redirect:/mainpageuser";
        }


        /* Ștergere utilizator */

        ReservationService reservationService;
        OrderService orderService;

        @DeleteMapping("/deleteUser")
        public String deleteUser (@RequestParam Long userId){
            User user = userService.getUserById(userId);
            if (user != null) {
                userService.deleteUser(userId);
            }
            return "redirect:/usersadmin";
        }
    }





