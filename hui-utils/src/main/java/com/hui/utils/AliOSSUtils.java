package com.hui.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pojo.AliOSSProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/*
 * 阿里云OSS
 *
 * 注入外部配置的属性
 * @ConfigurationProperties可以批量的将外部的属性配置注入到Bean对象的属性中
 * @Value注解只能一个一个的进行外部属性的注入
 *
 *
 * 阿里云对象存储OSS，云存储服务
 * 通过网络随时存储和调用包括文本、图片、音频和视频等在内的各种文件
 *
 * 使用步骤
 * 阿里云官网-开通对象存储-创建bucket-获取AccessKey秘钥-官网sdk文档编写入门程序
 * bucket：存储空间是用户用于存储对象（Object，就是文件）的容器，所有的对象都必须隶属于某个存储空间
 * SDK：软件开发工具包，包括辅助软件开发的依赖（jar包），代码示例等，都可以叫做SDK
 *
 * 阿里云 OSS 工具类
 * 自定义starter，定义一些公共组件，提供给各个项目团队使用。
 * 而在Springboot的项目中，一般会将这些公共组件封装为Springboot的starter
 *
 * 自定义starter
 * 创建aliyun-oss-spring-boot-starter模块【依赖管理功能】
 * 创建aliyun-oss-spring-boot-autoconfigure模块【自动配置功能】，在starter引入该模块
 * 在aliyun-oss-spring-boot-autoconfigure模块中定义自动配置功能，并定义自动配置文件META-INF/spring/xxx.imports
 *
 * */

@Component
public class AliOSSUtils {
   /* @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;*/

    @Autowired
    private AliOSSProperties aliOSSProperties;

    public AliOSSProperties getAliOSSProperties() {
        return aliOSSProperties;
    }

    public void setAliOSSProperties(AliOSSProperties aliOSSProperties) {
        this.aliOSSProperties = aliOSSProperties;
    }

    /*
   * 实现图片上传到OSS
   *
   * */
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
