/**
 * Service for loading user-specific data used for authentication and authorization.
 * <p>
 * This class implements the UserDetailsService interface, which is part of Spring Security.
 * It is responsible for retrieving user details from the database by username for authentication purposes.
 * </p>
 *
 * <p>
 * This service interacts with the UserRepository to fetch the user details, and it returns an instance
 * of UserPrincipal, which is a custom implementation of UserDetails.
 * </p>
 */


package com.example.Luana_Nature.security;

import com.example.Luana_Nature.model.User;
import com.example.Luana_Nature.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User-ul nu a fost găsit!");
            throw new UsernameNotFoundException("User-ul nu a fost găsit!");
        }

        return new com.example.Luana_Nature.security.UserPrincipal(user);
    }
}