package com.klef.jfsd.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.exam.model.Post;
import com.klef.jfsd.exam.model.User;



public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user); // Fetch posts by user
    List<Post> findByUserEmail(String email); // Fetch posts using user email
}
