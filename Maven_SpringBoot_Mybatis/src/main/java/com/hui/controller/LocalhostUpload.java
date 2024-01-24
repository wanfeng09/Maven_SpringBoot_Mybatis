package com.hui.controller;

import com.hui.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.Result;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
/*
* 本地存储【图片】
* 【缺点：无法直接访问、磁盘空间限制、磁盘损坏】
* 在服务端，接收到上传文件，将文件存储在本地磁盘中
* 默认单个文件允许最大大小为1M，若上传大文件配置application.properties
* 配置单个文件最大上传大小
* spring.servlet.multipart.max-file-size=10MB
* 配置单个请求最大上传大小（一次请求可以上传多个文件）
* spring.servlet.multipart.max-request-size=100MB
*
* */
@RestController
public class LocalhostUpload {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    /*
    * 本地上传
    * */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        // 获取原始文件名
        String originalFileName = file.getOriginalFilename();
        // 构建新的文件名
        String fileName = UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf("."));
        // 将文件保存在D:\java\demo\image
        file.transferTo(new File("D:\\java\\demo\\image\\" + fileName));
        return Result.success();
    }

    /*
    * 阿里云上传
    * */
    @PostMapping("/uploadOSS")
    public Result uploadOSS(MultipartFile file) throws IOException {
        String url = aliOSSUtils.upload(file);
        return Result.success(url);

    }
}
