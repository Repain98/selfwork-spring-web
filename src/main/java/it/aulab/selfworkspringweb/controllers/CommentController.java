package it.aulab.selfworkspringweb.controllers;

import it.aulab.selfworkspringweb.models.Comment;
import it.aulab.selfworkspringweb.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable("id") Long id) {
        return commentRepository.findById(id).get();
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("{id}")
    public Comment updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentRepository.save(comment);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
    }
}