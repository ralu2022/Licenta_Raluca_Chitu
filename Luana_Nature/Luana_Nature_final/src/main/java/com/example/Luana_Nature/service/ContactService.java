/**
 * Service class for managing messages received via contact page. This class interacts with the ContactRepository
 * to perform CRUD operations of create, read, delete, except update.
 * <p>
 * The ContactService class does not provide an update method as nighter the user nor the administrator are not
 * modifying the messages.
 * </p>
 */

package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Contact;
import com.example.Luana_Nature.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    /* READ */
    public List<Contact> getAllContactMessages() {
        return contactRepository.findAll();
    }

    /* CREATE */
    public void addContactMessage(String name, String email, String message) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);
        contactRepository.save(contact);
    }

    /* DELETE */
    public void deleteContactMessage(Long contactMessageId) {
        Contact contactMessage = contactRepository.findById(contactMessageId).orElseThrow(() -> new IllegalArgumentException(
                "Mesajul nu există"));
        contactRepository.delete(contactMessage);
    }

    public Contact getContactMessageById(Long contactMessageId) {
        return contactRepository.findById(contactMessageId).orElse(null);
    }

}
