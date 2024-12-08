package com.klef.jfsd.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.jfsd.exam.model.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByAppliedUserEmail(String appliedUserEmail); // Applications for a user by email
    List<Application> findByPoliticianEmail(String politicianEmail); // Applications assigned to a politician
}
