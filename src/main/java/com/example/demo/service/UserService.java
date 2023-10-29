package com.example.demo.service;

import com.example.demo.dao.UserRepo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Users;
import com.example.demo.payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    public int add(UserDto user) {
        Users newuser = modelMapper.map(user,Users.class);
        Timestamp createt = new Timestamp(System.currentTimeMillis());
        newuser.setCreatedDate(createt);
        Users user1 = userRepo.save(newuser);
        return user1.getUserId();
    }

    public List<Users> findByid (String id){
        List<Users> users = new ArrayList<>();
        if(id == null){
            users =  userRepo.findAll();
        }else{
            int userid=Integer.valueOf(id);
            if(userRepo.findById(userid).isPresent()){
                Users user = userRepo.findById(userid).get();
                users.add(user);
            }else {
              throw new ResourceNotFoundException("User","user id",userid);
            }
        }
        return users;
    }
    public Users updatebyId(UserDto userDto,int id){
        Users user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user id",id));
        user.setName(userDto.getName());
        user.setPass(userDto.getPass());
        user.setEmail(userDto.getEmail());
        user.setPhoneNum(userDto.getPhoneNum());
        Timestamp updatet = new Timestamp(System.currentTimeMillis());
        user.setUpdatedDate(updatet);
        Users usernew = userRepo.save(user);
        return usernew;
    }

    public void deletebyid(int id){
        Users user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user id",id));
        userRepo.deleteById(id);
    }

}
