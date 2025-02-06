/**
 * Interface used to handle CRUD (create, read, update, delete) operations on the Contact entity.
 *
 * <p>This interface extends the JPARepository interface, which provides built-in CRUD methods.</p>
 */

package com.example.Luana_Nature.repository;

import com.example.Luana_Nature.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
