package com.klef.jfsd.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.jfsd.exam.model.Application;
import com.klef.jfsd.exam.repository.ApplicationRepository;
import com.klef.jfsd.exam.repository.UserRepository;
import com.klef.jfsd.exam.model.User;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Application> getApplicationsByUser(String userEmail) {
        // Validate the user email exists
        if (userRepository.findByEmail(userEmail) == null) {
            throw new IllegalArgumentException("Invalid user email.");
        }

        // Retrieve the applications
        return applicationRepository.findByAppliedUserEmail(userEmail);
    }

    public List<Application> getApplicationsByPolitician(String politicianEmail) {
        // Validate the politician email exists
        if (userRepository.findByEmail(politicianEmail) == null) {
            throw new IllegalArgumentException("Invalid politician email.");
        }

        // Retrieve the applications
        return applicationRepository.findByPoliticianEmail(politicianEmail);
    }

    public Application saveApplication(Application application) {
    	
       

        // Save and return the application
        return applicationRepository.save(application);
    }
}
