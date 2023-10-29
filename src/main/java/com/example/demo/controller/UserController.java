package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.payloads.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDto requser){
        int id = userService.add(requser);
        return new ResponseEntity<>("user created " + id, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public List<Users> findUsers(@Nullable @RequestParam  String id){
        return userService.findByid(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@Valid @RequestBody UserDto requser){
        userService.updatebyId(requser,id);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> update(@PathVariable int id){
        userService.deletebyid(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
