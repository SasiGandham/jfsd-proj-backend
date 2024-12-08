package com.klef.jfsd.exam.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.exam.model.Post;
import com.klef.jfsd.exam.model.User;
import com.klef.jfsd.exam.repository.PostRepository;
import com.klef.jfsd.exam.repository.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    public UserRepository userRepository;

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable).getContent();
    }
    
    public Post addPost(String email, String content, String image) {
        // Find user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        
        System.out.println(user+" "+ content +" "+ image);
        	
        // Create a new Post instance
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setImage(image);
        post.setCreatedAt(LocalDateTime.now());
        post.setLikes(0); // Initialize likes to 0

        // Save the Post to the database
        return postRepository.save(post);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
