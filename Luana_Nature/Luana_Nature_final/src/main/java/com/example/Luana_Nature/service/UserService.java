/**
 * Service class for managing users. This class interacts with the UserRepository
 * to perform CRUD operations of create, read, update and delete.
 */


package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void addUser(String name, String email, String username, String password, String role) {

        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("User-ul deja există!");
        }

        role = "user";
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(role);
        userRepository.save(user);
    }

    public boolean isValidUser(String username, String password) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
