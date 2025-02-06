/*
 * Interface used to handle CRUD (create, read, update, delete) operations on the User entity.
 *
 * <p>This interface extends the JPARepository interface, which provides built-in CRUD methods.</p>
 * <p>Also, it declares the built-in findByUserName() method used to find users based on their usernames.</p>
 */


package com.example.Luana_Nature.repository;


import com.example.Luana_Nature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}