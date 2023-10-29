package com.example.demo.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int postId;
    private String imageName = "radha.jpg";
    @NotNull
    @NotBlank(message = "Please enter Post title")
    private String title;
    @NotNull
    @NotBlank(message = "Please enter post content")
    private String content;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> commentsList = new ArrayList<>();


}
