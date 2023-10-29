package com.example.demo.service;
import com.example.demo.dao.CategoryRepo;
import com.example.demo.dao.PostRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.example.demo.model.Users;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileService fileService;

    public PostDto savePost(PostDto postdata,int userid,int catid) {
        Users user = userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","user id",userid));
        Category category = categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("Category","category id",catid));

        Post post = modelMapper.map(postdata,Post.class);
        post.setCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        postRepo.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    public void delete(int id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","post id",id));
        postRepo.delete(post);
    }

    public List<PostDto> findbyCatid(int id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","category id",id));
        List<Post> postList =  postRepo.findByCategory(category);
        List<PostDto> posts = postList.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return posts;
    }

    public List<PostDto> findbyuser(int id) {
        Users user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","user id",id));
        List<Post> postList =  postRepo.findByUser(user);
        List<PostDto> postsbycat = postList.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsbycat;
    }

    public PostDto updatePost(PostDto postDto,int postid) {
        Post post1 = postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post","post id",postid));
            post1.setTitle(postDto.getTitle());
            post1.setContent(postDto.getContent());
            post1.setUpdatedDate(new Date());
            Post newpost =  postRepo.save(post1);
            return modelMapper.map(newpost,PostDto.class);
    }

    public PostResponse getAll(int pageno, int pagesize,String sortby) {
        Pageable p = PageRequest.of(pageno,pagesize, Sort.by(sortby));
        Page<Post> postPage = postRepo.findAll(p);
        List<Post> allPost = postPage.getContent();
        List<PostDto> postDtos = allPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPostDtoList(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    public List<PostDto> findBYTitle(String title){
        List<Post> posts = postRepo.findByTitleContaining(title);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


    public PostDto uploadImage(MultipartFile image, int postid, String path) throws IOException {
        Post post = postRepo.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postid));
        String imageName = fileService.uploadImage(path, image);
        post.setImageName(imageName);
        Post post1 = postRepo.save(post);
        PostDto map = modelMapper.map(post1, PostDto.class);
        return map;
    }

    public InputStream serveImage(String path, String imageName) throws FileNotFoundException {
        return fileService.getImage(path,imageName);
    }
}
