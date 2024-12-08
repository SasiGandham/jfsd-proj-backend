package com.klef.jfsd.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.jfsd.exam.dto.LoginRequest;
import com.klef.jfsd.exam.model.User;
import com.klef.jfsd.exam.repository.UserRepository;
import com.klef.jfsd.exam.service.MailService;
import com.klef.jfsd.exam.service.UserService;
import com.klef.jfsd.exam.utils.GeoUtils;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        // Save the user and get the plain password from the service
        User savedUser = userService.saveUser(user);

        // Check if the user's role is "politician"
        if ("politician".equalsIgnoreCase(user.getRole())) {
            String subject = "Welcome to Our Platform!";
            String body = "Dear " + user.getName() + ",\n\n"
                    + "Your account has been successfully created as a politician.\n\n"
                    + "Email: " + user.getEmail() + "\n"
                    + "Password: " + savedUser.getPassword2() + "\n\n"
                    + "Thank you for joining our platform!\n\n"
                    + "Best regards,\n"
                    + "Your Team";

            // Handle any potential errors in email sending
            try {
                mailService.sendEmail(user.getEmail(), subject, body);
                System.out.println("Email sent successfully to " + user.getEmail());
            } catch (Exception e) {
                System.err.println("Error sending email: " + e.getMessage());
            }
        }

        // Find and set the nearest politician email for the user if not a politician
        if (!"politician".equalsIgnoreCase(user.getRole())) {
            String nearestPoliticianEmail = userService.findNearestPoliticianEmail(user.getLatitude(), user.getLongitude());
            savedUser.setNearestPoliticianEmail(nearestPoliticianEmail);
            userRepository.save(savedUser); // Save the updated user
        }

        // Remove the plain password from the response before returning the user
        savedUser.setPassword(null);
        return savedUser;
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail() + " " + loginRequest.getPassword());
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("role", user.getRole());
        response.put("name", user.getName());
        response.put("image", user.getPhoto());
        response.put("nearest_politician_email", user.getNearestPoliticianEmail());
        return response;
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userRepository.findByRole(role);
    }

    
}
