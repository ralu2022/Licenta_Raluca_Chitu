/*
 * Controller used to handle CRUD (create, read, update, delete) operations upon Contact entity.
 *
 * At the present moment only read and create operations are defined, but for future development update and delete will
 * be implemented.
 *
 * */

package com.example.Luana_Nature.controller;


import com.example.Luana_Nature.model.Contact;
import com.example.Luana_Nature.service.ContactService;
import com.example.Luana_Nature.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final EmailService emailService;

    /* Vizualizare mesaje primite */

    @GetMapping("/allcontactmessagesadmin")
    public String allContactMessages(Model model) {
        model.addAttribute("contactmessagesadmin", contactService.getAllContactMessages());
        return "contactmessagesadmin";
    }

    /* Adăugare mesaj */

    @GetMapping("/contactMessage")
    public String contactMessage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactmessages";
    }

    @PostMapping("/addContactMessage")
    public String addContactMessage(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String message) {
        contactService.addContactMessage(name, email,message);

        String subject = "Mesaj nou";
        String text = "Aveți un nou mesaj de la " + name + ": " + "\n" + message;
        String adminEmail = "luananature2024@gmail.com";
        emailService.sendAdminEmail(adminEmail, subject, text);

        return "redirect:/contactMessages/contactMessage";
    }

    @GetMapping("/contactMessageUser")
    public String contactMessageUser(Model model) {
        model.addAttribute("contactuser", new Contact());
        return "contactmessagesuser";
    }

    @PostMapping("/addContactMessageUser")
    public String addContactMessageUser(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String message) {
        contactService.addContactMessage(name, email,message);

        String subject = "Mesaj nou";
        String text = "Aveți un nou mesaj de la " + name + ": " + "\n" + message;
        String adminEmail = "luananature2024@gmail.com";
        emailService.sendAdminEmail(adminEmail, subject, text);

        return "redirect:/contactMessages/contactMessageUser";
    }

    @DeleteMapping("/deleteMessage")
    public String deleteMessage(@RequestParam Long contactMessageId) {
        Contact contactMessage = contactService.getContactMessageById(contactMessageId);
                if(contactMessage != null) {
                    contactService.deleteContactMessage(contactMessageId);
                }
                return "redirect:/contactMessages/allcontactmessagesadmin";
    }

}
