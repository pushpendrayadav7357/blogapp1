package com.example.demo.exception;

import lombok.Data;

@Data
public class UsernotCreateThisPostException extends RuntimeException{
    private String userName;
    private int postId;

    public UsernotCreateThisPostException(String userName,int postid) {
        super(String.format(" %s not create this post by id %d ", userName, postid));
        this.userName = userName;
        this.postId = postid;
    }
}
