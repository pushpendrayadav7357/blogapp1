package com.example.demo.controller;


import com.example.demo.config.Constants;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.service.FileService;
import com.example.demo.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/save")
    public ResponseEntity<PostDto> savePost(@Valid @RequestBody PostDto postdata,@RequestParam int userid,@RequestParam int categoryid){
       PostDto post = postService.savePost(postdata,userid,categoryid);
        return new ResponseEntity<>(post, HttpStatus.CREATED);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        postService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @GetMapping("/post-by-category/{id}")
    public ResponseEntity<List<PostDto>> getPostbyCategory(@PathVariable int id){
       return new ResponseEntity<>(postService.findbyCatid(id),HttpStatus.OK);
    }
    @GetMapping("/post-by-user/{id}")
    public ResponseEntity<List<PostDto>> getPostbyUser(@PathVariable int id){
        return new ResponseEntity<>(postService.findbyuser(id),HttpStatus.OK);
    }
    @PutMapping("/update-post/{postid}")
    public PostDto updatePost(@Valid @RequestBody PostDto postDto,@PathVariable int postid){
        PostDto post = postService.updatePost(postDto,postid);
        return post;
    }
    @GetMapping("/get-all-post")
    public List<PostDto> getAllpost(){
        return postService.getAllPosts();
    }
    @GetMapping("/get-all")
    public ResponseEntity<PostResponse> getAllpost(
            @RequestParam(value = "pageno",defaultValue = Constants.PAGE_NUM,required = false)int pageno ,
            @RequestParam(value = "pagesize",defaultValue = Constants.PAGE_SIZE,required = false) int pagesize,
            @RequestParam(value = "sortby",defaultValue = Constants.SORT_BY,required = false) String sortby
            ){
        return new ResponseEntity<>(postService.getAll(pageno,pagesize,sortby),HttpStatus.OK);
    }
    @GetMapping("/search/by-title/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
        List<PostDto> posts = postService.findBYTitle(keywords);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @PostMapping("/upload-image/{postid}")
    public PostDto postImage(@RequestParam MultipartFile image,@PathVariable int postid) throws IOException {
      return postService.uploadImage(image,postid,path);
    }
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveFile(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream stream = postService.serveImage(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream,response.getOutputStream());
    }

}
