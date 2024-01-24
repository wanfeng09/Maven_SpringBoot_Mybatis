package com.aliyun.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    @Autowired
    private AliOSSProperties aliOSSProperties;

    // 添加getter/setter方法
    public AliOSSProperties getAliOSSProperties() {
        return aliOSSProperties;
    }

    public void setAliOSSProperties(AliOSSProperties aliOSSProperties) {
        this.aliOSSProperties = aliOSSProperties;
    }

    public String upload(MultipartFile file) throws IOException {
        // 获取阿里云oss参数
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFileName = file.getOriginalFilename();
        // String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        // 指定目录
        String fileName = "test/"+UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println(fileName);

        // 上传文件到oss
        OSS oss = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        oss.putObject(bucketName,fileName,inputStream);

        // 文件访问路径
        String url = endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1] + "//" + fileName;
        // 关闭ossClient
        return url;
    }

}
