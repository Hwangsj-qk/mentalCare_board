package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.service.CommentService;
import com.busanit.mentalCare.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComment() {
        List<CommentDTO> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long comment_id) {
        CommentDTO comment = commentService.getCommentById(comment_id);
        if(comment != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment) {
        CommentDTO createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment (@PathVariable Long comment_id, @RequestBody CommentDTO updateComment) {
        CommentDTO comment = commentService.updateComment(comment_id, updateComment);

        if(comment != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long comment_id) {
        if(!commentService.deleteComment(comment_id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }





}