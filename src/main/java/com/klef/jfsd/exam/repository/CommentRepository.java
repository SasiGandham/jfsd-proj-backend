package com.klef.jfsd.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.exam.model.Comment;
import com.klef.jfsd.exam.model.Post;
import com.klef.jfsd.exam.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post); // Fetch comments for a specific post
    List<Comment> findByUser(User user); // Fetch comments made by a specific user
}
