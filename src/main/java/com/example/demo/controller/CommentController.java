package com.example.demo.controller;

import com.example.demo.payloads.CommentDto;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/add")
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto commentDto ,
                                                 @RequestParam int userId,@RequestParam int postId){
        CommentDto response = commentService.addcomment(commentDto,userId,postId);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam int commentId,@RequestParam int userId,@RequestParam int postId) {
     commentService.deleteComment(commentId,userId,postId);
     return new ResponseEntity<>("Deleted",HttpStatus.OK);

    }


    }
