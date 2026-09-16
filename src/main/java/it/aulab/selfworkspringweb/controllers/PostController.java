package it.aulab.selfworkspringweb.controllers;

import it.aulab.selfworkspringweb.models.Comment;
import it.aulab.selfworkspringweb.models.Post;
import it.aulab.selfworkspringweb.repositories.CommentRepository;
import it.aulab.selfworkspringweb.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    public Post getPost(@PathVariable("id") Long id) {
        return postRepository.findById(id).get();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        post.setId(id);
        return postRepository.save(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            List<Comment> postComments = post.getComments();
            for (Comment comment : postComments) {
                commentRepository.deleteById(comment.getId());
            }
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }
}