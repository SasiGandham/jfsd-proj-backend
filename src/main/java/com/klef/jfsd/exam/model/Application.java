package com.klef.jfsd.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "allapplications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applied_user_email", nullable = false)
    private String appliedUserEmail; // Email of the user who applied

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String problem;

    private String description;
    private String image;
    private String location;

    @Column(name = "politician_email", nullable = false)
    private String politicianEmail; // Email of the assigned politician

    @Enumerated(EnumType.STRING)
    private Progress progress = Progress.RECEIVED;

    public enum Progress {
        RECEIVED, IN_PROGRESS, COMPLETED
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppliedUserEmail() {
        return appliedUserEmail;
    }

    public void setAppliedUserEmail(String appliedUserEmail) {
        this.appliedUserEmail = appliedUserEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoliticianEmail() {
        return politicianEmail;
    }

    public void setPoliticianEmail(String politicianEmail) {
        this.politicianEmail = politicianEmail;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
