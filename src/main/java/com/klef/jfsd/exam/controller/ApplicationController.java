package com.klef.jfsd.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.klef.jfsd.exam.model.Application;
import com.klef.jfsd.exam.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create")
    public Application saveApplication(@RequestBody Application application) {
        try {
            return applicationService.saveApplication(application);
        } catch (Exception ex) {
            logger.error("Error saving application: {}", ex.getMessage());
            return null;  // or return an error message as a string if needed
        }
    }

    @GetMapping("/user/{userEmail}")
    public Object getApplicationsByUser(@PathVariable String userEmail) {
        try {
            if (userEmail == null || userEmail.isEmpty()) {
                return "Error: User email is missing or invalid";
            }

            logger.info("Fetching applications for user: {}", userEmail);
            List<Application> applications = applicationService.getApplicationsByUser(userEmail);

            if (applications.isEmpty()) {
                return "No applications found for the given user.";
            }

            return applications;
        } catch (Exception ex) {
            logger.error("Error fetching applications for user {}: {}", userEmail, ex.getMessage());
            return "Error fetching applications: " + ex.getMessage();
        }
    }

    @GetMapping("/politician/{politicianEmail}")
    public Object getApplicationsByPolitician(@PathVariable String politicianEmail) {
        try {
            if (politicianEmail == null || politicianEmail.isEmpty()) {
                return "Error: Politician email is missing or invalid";
            }

            logger.info("Fetching applications for politician: {}", politicianEmail);
            List<Application> applications = applicationService.getApplicationsByPolitician(politicianEmail);

            if (applications.isEmpty()) {
                return "No applications found for the given politician.";
            }

            return applications;
        } catch (Exception ex) {
            logger.error("Error fetching applications for politician {}: {}", politicianEmail, ex.getMessage());
            return "Error fetching applications: " + ex.getMessage();
        }
    }
}
