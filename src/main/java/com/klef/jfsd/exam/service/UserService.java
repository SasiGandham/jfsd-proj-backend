package com.klef.jfsd.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.jfsd.exam.model.User;
import com.klef.jfsd.exam.repository.UserRepository;
import com.klef.jfsd.exam.utils.GeoUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate a random 8-character password
    }

    public User saveUser(User user) {
        String plainPassword = generatePassword(); // Generate the plain password
        user.setPassword(passwordEncoder.encode(plainPassword)); // Encrypt the password for storage
        User savedUser = userRepository.save(user);

        // Temporarily store the plain password in the User object for email
        savedUser.setPassword2(plainPassword);
        return savedUser;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User login(String email, String password) {
        User user = getUserByEmail(email);
        
        System.out.println(password+" "+ user.getPassword());
        
        if (user != null && (passwordEncoder.matches(password, user.getPassword()) || password.equals(user.getPassword2()  ))) {
            return user; // Return the whole User object
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    public String findNearestPoliticianEmail(double userLat, double userLon) {
        List<User> politicians = userRepository.findByRole("politician"); // Get all politicians
        User nearestPolitician = null;
        double minDistance = Double.MAX_VALUE;

        for (User politician : politicians) {
            if (politician.getLatitude() != null && politician.getLongitude() != null) {
                double distance = GeoUtils.calculateDistance(userLat, userLon, politician.getLatitude(), politician.getLongitude());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPolitician = politician;
                }
            }
        }

        return (nearestPolitician != null) ? nearestPolitician.getEmail() : null;
    }
}
