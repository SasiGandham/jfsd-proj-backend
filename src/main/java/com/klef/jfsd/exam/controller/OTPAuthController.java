package com.klef.jfsd.exam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.jfsd.exam.service.OTPService;

import java.util.Map;

@CrossOrigin(allowedHeaders = "*")

@RestController
@RequestMapping("/api/auth")
public class OTPAuthController {
    @Autowired
    private OTPService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String aadharNumber = request.get("aadharNumber");

        // Validate inputs
        if (email == null || email.isEmpty() || aadharNumber == null || aadharNumber.length() != 12) {
            return ResponseEntity.badRequest().body("Invalid email or Aadhar number.");
        }

        String otp = otpService.generateOTP(email);
        return ResponseEntity.ok("OTP sent to " + email);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String enteredOtp = request.get("otp");
        System.out.println("From Auth"+' '+ email+enteredOtp);
        if (otpService.verifyOTP(email, enteredOtp)) {
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
    }
}
