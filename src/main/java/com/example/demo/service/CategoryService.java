package com.example.demo.service;

import com.example.demo.dao.CategoryRepo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.payloads.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        categoryRepo.save(category);
    }

    public List<Category> findByid (String id){
        List<Category> categoryList = new ArrayList<>();
        if(id == null){
            categoryList =  categoryRepo.findAll();
        }else{
            int catid =Integer.valueOf(id);
            if(categoryRepo.findById(catid).isPresent()){
                Category category = categoryRepo.findById(catid).get();
                categoryList.add(category);
            }else {
                throw new ResourceNotFoundException("Category","category id",catid);
            }
        }
        return categoryList;
    }
    public Category updatebyId(CategoryDto category, int id){
        Category newcategory = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user id",id));
        newcategory.setCatTitle(category.getCatTitle());
        newcategory.setCatDescription(category.getCatDescription());
        categoryRepo.save(newcategory);
        return newcategory;
    }

    public void deletebyid(int id){
        Category category = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user id",id));
        categoryRepo.deleteById(id);
    }

}
