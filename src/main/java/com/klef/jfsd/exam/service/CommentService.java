package com.klef.jfsd.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.klef.jfsd.exam.model.Comment;
import com.klef.jfsd.exam.model.Post;
import com.klef.jfsd.exam.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
