package com.example.demo.dao;

import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
        List<Post> findByCategory(Category category);
        List<Post> findByUser(Users users);
        List<Post> findByTitleContaining(String title);
}
