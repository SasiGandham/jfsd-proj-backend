package com.klef.jfsd.exam.dto;

import jakarta.persistence.Column;

public class PostRequest {
    private String email;
    private String content;
    @Column(columnDefinition = "LONGTEXT")
    private String image; // Optional
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
 
    
}
