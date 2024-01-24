package com.example.controller;

import com.aliyun.oss.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("uploadOSS")
    public String uploadOSS(MultipartFile file) throws IOException {
        String url = aliOSSUtils.upload(file);
        return url;
    }
}
