package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String filepath = path + File.separator + name;
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return name;
    }
    public InputStream getImage(String path,String filename) throws FileNotFoundException {
        String fullpath = path+File.separator+filename;
        InputStream inputStream = new FileInputStream(fullpath);
        return inputStream;

    }

}
