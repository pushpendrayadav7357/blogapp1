package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;
    @PostMapping("/save")
    public ResponseEntity<CategoryDto> saveCat(@Valid @RequestBody CategoryDto categoryDto){
        service.save(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public List<Category> findCategories(@Nullable @RequestParam  String id){
        return service.findByid(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable int id,@Valid @RequestBody CategoryDto category){
        Category category1 = service.updatebyId(category,id);
        return new ResponseEntity<Category>(category1,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> update(@PathVariable int id){
        service.deletebyid(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
