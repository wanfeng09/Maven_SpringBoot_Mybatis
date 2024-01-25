## SpringBoot
快速构架应用程序、简化开发、提高效率

Springboot（SSM）
SpringMVC：接受请求、响应数据、拦截器、全局异常处理
SpringFramework：IOC、IO、AOP、事务管理
Mybatis：Mybatis

JavaWeb：过滤器、cookie、session
解决方案：JWT、阿里云oss


```bash
# 生成iml文件
mvn idea:module
```

自定义starter模块
1. 创建Spring Initializr定义aliyun起步依赖模块【aliyun-oss-spring-boot-starter】
保留iml/pom.xml文件

```xml
	 <!-- 删除以下多余内容 -->
	<name>aliyun-oss-spring-boot-starter</name>
    <description>aliyun-oss-spring-boot-starter</description>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

		<!-- 引用自动配置依赖-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-oss-spring-boot-autoconfigure</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

2. 创建Spring Initializr定义aliyun自动配置模块【aliyun-oss-spring-boot-autoconfigure】
保留iml/pom.xml/src文件，删除AliyunOssSpringBootAutoconfigureApplication.java启动类

		1、在External Libraries下找到autoconfigure下的META-INF/spring/org...(复制imports名字)
		2、在aliyun-oss-spring-boot-autoconfigur的src/main/resources下创建目录META-INF/spring，然后新建文件（文件名是第一步复制的imports名字）
		3、在src/main/java创建包com.aliyun.oss，然后创建AliOSSUtils/AliOSSProperties/AliOSSAutoConfigure的class类
		4、将AliOSSAutoConfigure文件路径复制到第二步文件内容


```xml
	 <!-- 删除以下多余内容 -->
	<name>aliyun-oss-spring-boot-autoconfigure</name>
    <description>aliyun-oss-spring-boot-autoconfigure</description>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
     <!--引入相关依赖-->
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <!--阿里云OSS-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>3.15.1</version>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-runtime</artifactId>
            <version>2.3.3</version>
        </dependency>
```

3. 创建测试项目，引入aliyun-oss-spring-boot-starter依赖
	

```xml
<!-- 自定义starter-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-oss-spring-boot-starter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

## MyBatis
持久层框架，用于简化JDBC的开发
[官网](https://blog.mybatis.org/)

**JDBC介绍**
Java DataBase Connectivity：使用Java语言操作关系型数据库的一套API

>**本质**
	 - sun公司官方定义的一套操作所有关系型数据库的规范，即接口
	 - 各个数据库厂商去实现这套接口，提供数据库驱动jar包
	 - 接口编程，真正执行的代码是驱动jar包中的实现类

**实现步骤**

 1. 准备工作（创作springboot工程、数据库表user、实体类user）
 2. 引入mybatis的相关依赖，配置mybatis（数据库连接信息）
 3. 在注解上编写SQL语句

```application.properties
#配置数据库的连接信息  四要素
#驱动类名称
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#数据库连接的ur1
spring.datasource.url=jdbc:mysql://localhost:3306/db_test_01
#连接数据库的用户名
spring.datasource.username=root
#连接数据库的密码
spring.datasource.password=123456
#配置mybatis的日志，指定输出到控制台
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
#开启驼峰命名自动映射，即从数据库字段名create_time映射到Java属性名createTime
mybatis.configuration.map-underscore-to-camel-case=true
```
### 快速入门
IDEA--->New Project--->Spring Initializr，修改相应配置（注意Type:选择Maven）--->点击Next--->Web:勾选Spring Web【MyBatis：sql勾选mybatis Framework、mysql Driver】--->点击Finsh

注意：Server Url的链接对应ui可视化配置
Spring Boot ：3开头的版本JDK版本必须是17以上
可删除.mvn/HELP.md/mvnw/mvnw.cmd/文件

##### 起步依赖
spring-boot-starter-web：包含了web应用开发所需要的常见依赖
spring-boot-starter-test：包含了单元测试所需要的常见依赖
[官网](https://docs.spring.io/spring-boot/)

##### 内嵌Tomcat服务器
基于spring-boot开发的web应用程序，内置了Tomcat服务器，当启动类运行时，会自动启动内嵌的Tomcat服务器。

#### 启动项目

 1. 运行启动类：xxxApplication
 2. 定义一个测试java
 3. 查看控制器返回端口号：

> Tomcat initialized with port 8080 (http)
> Tomcat started on port 8080 (http) with context path ''


定义请求处理类
```java
package com.hui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 请求处理类
// 浏览器访问地址：http://localhost:8080/hello
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
```

#### 配置sql提示
@Select("选中sql语句")右键show context action---inject language or reference---找到MySQL(SQL)


### 数据库连接池

 - 数据库连接器是个容器，负责分配、管理数据库连接
 - 它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个
 - 释放空间时间超过最大空闲时间的连接，来避免因为没有释放连接而引起的数据库连接遗漏

==优点：资源复用，提升系统响应速度，避免数据库连接遗漏==

标准接口：DataSource
常见产品：C3P0/DBCP/Druid（德鲁伊）/HiKari
Druid（德鲁伊）：阿里巴巴开源数据库连接池项目
[Druid](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter)

```xml
<!-- 切换连接池 -->
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid-spring-boot-starter</artifactId>
   <version>1.1.17</version>
</dependency>
```

**预编译SQL**
优点：性能更高、更安全（防止SQL注入）
sql注入通过操作输入的数据来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法
```sql
-- 不安全的sql【sql注入】
select * from tb_user where username='root' and password = '' or '1' = '1';
delete from tb_user where id = 1;
delete from tb_user where id = 2;
delete from tb_user where id = 3;

-- 预编译【语句执行一次】
-- delete from tb_user where id = ?;
-- 1
-- 2
-- 3
```

运行jar包

```bash
java -jar xxx.jar
```


**lombok**
通过注释的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，并可以自动化生辰日志变量，简化Java开发，提高效率
注意：lombok会在编译时，自动生成对应的Java代码。使用lombok时，需要安装lombok插件（IDEA自带）

```xml
		<!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

|注解|作用|
|:-:|:-:|
|@Getter/@Setter|为所有的属性提供get/set方法|
|@ToString|会给类自动生成易读的toString方法|
|@EqualsAndHashCode|根据类所拥有的非静态字段自动重写equals方法和hashCode方法|
|@Data|提供了更综合的生成代码功能（get/set/toString/equals/hashCode）|
|@NoArgsConstructor|为实体类生成无参的构造器方法|
|@AllArgsConstructor|为实体类生成除了static修饰的字段之外带有各参数的构造器方法|

**数据封装**
实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装。
如果实体类属性名和数据库表查询返回的字段名不一致，不能自动封装。
>解决
**方式1:起别名**
在sql语句中，对不一样的列名起别名，别名和实体类属性名一样
@Select("select id,username 别名,realName from tb_user where id=#{id}")
**方式2:手动结果映射**
通过@Results及@Result进行手动结果映射。
@Select("select * from tb_user where id=#{id}")
@Results({
		@Result(column="username",property="别名"),
		@Result(column="create_time",property="createTime")
})
**方式3:开启驼峰命名** ==推荐==
如果字段名与属性名符合驼峰命名规则，mybatis会自动通过驼峰命名规则映射
#开启驼峰命名自动映射，即从数据库字段名create_time映射到Java属性名createTime
mybatis.configuration.map-underscore-to-camel-case=true


**XML映射文件**
[mybatis](https://mybatis.net.cn/getting-started.html)

>规范
XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）
XML映射文件的namespace属性为Mapper接口全限定名一致。
XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致。


 - 在resource右键new---Directory---（同包同名：注意创建路径用/）
 - 安装mybatisX插件
 - 使用mybatis的注解，主要是来完成一些简单的增删改查功能。如果需要实现复杂的sql功能，建议使用xml配置映射语句

>if:用于判断条件是否成立。使用test属性进行条件判断，如果条件为true，则拼接sql。
where：只会在子元素有内容的情况下才插入where子句。而且会自动去除子句的开头的and或or
set:动态的在行首插入set关键字，并且删掉额外的逗号（用在update语句中）
foreach【collection：集合名称，item：集合遍历出来的元素、separator：分隔符、open：遍历开始前拼接的片段、close：遍历结束后拼接的片段】
sql：定义可重复的sql片段
include：通过属性refid，指定包含的sql片段

### Filter
**filter过滤器**（jakarta.servlet）
* javaWeb三大组件（Servlet、Filter、Listener）之一。
* 过滤器可以把对资源的请求拦截下来，从而实现一些特殊的功能。
* 过滤器一般完成一些通用的操作，比如：登录效验、统一编码处理、敏感字符处理等。

步骤
1. 定义Filter：定义一个类，实现Filter接口，并重写其所有方法。
2. 配置Filter：Filter类上加@WebFilter注解，配置拦截资源的路径。引导类上加@ServletComponentScan开启Servlet组件支持。

|拦截路径|urlPatterns值|含义
|:-:|:-:|:-:|
|拦截具体路径|/login|只能访问/login路径时，才会被拦截|
|目录拦截|/user/*|访问/user下的所有资源，都会被拦截|
|拦截所有|/*|访问所有资源，都会被拦截|

 ###  拦截器Interceptor
* 是一种动态拦截方法调用的机制，类似于过滤器。Spring框架中提供的，用来动态拦截控制器方法的执行。
* 作用：拦截请求，在指定的方法调用前后，根据业务需要执行预设的代码

步骤
1、定义拦截器，实现HandlerInterceptor接口，并重写其所有方法。
2、注册拦截器

 |拦截路径|含义|
|:-:|:-:|
|/*|一级路径|
|/**|任意级路径|
|/user/*|user下的一级路径|
|/user/**|user下的任意级路径|

**filter与interceptor**
接口规范不同：过滤器需要实现Filter接口，而拦截器需要实现HandlerInterceptor接口。
拦截范围不同：过滤器Filter会拦截所有的资源，而Interceptor只会拦截Spring环境中的资源。

### 全局异常处理
@RestControllerAdvice类上
@ExceptionHandle(Exception.class)方法上

### 事务管理
注解： @Transactional【类、方法、接口】
将当前方法交给Spring进行事务管理，方法执行前，开启事务；成功执行完毕，提交事务；出现异常，回滚事务
**@Transactional(rollbackFor = Exception.class)**
rollbackFor默认情况下，只有出现RuntimeException才回滚异常。rollbackFor属性用于控制何种异常类型，回滚事务。

#### 事务属性-传播
事务的传播行为：一个事务方法被另一个事务方法调用。
**@Transactional(propagation = Propagation.REQUIRES_NEW)**
|属性值|含义|
|:-:|:-:|
|REQUIRED|默认，需要事务，有就加入，无则创建新事务|
|REQUIRES_NEW|创建新事务|

### AOP
Aspect Oriented Programming：面向特定方法编程

```xml
		<!-- AOP-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
```
使用场景：记录操作日志/权限控制/事务管理/...
优势：减少重复代码、提高开发效率、维护方便
* 连接点：JoinPoint，可以被AOP控制的方法（暗含方法执行时的相关信息）
* 通知：Advice，指那些重复的逻辑，也就是共性功能（最终体现为一个方法）
* 切入点：PointCut，匹配连接点的条件，通知仅会在切入点方法执行时被应用
* 切面：Aspect，描述通知与切入点的对应关系
* 目标对象：Target，通知所应用的对象

|通知类型|说明|
|:-:|:-:|
|@Around|环绕通知，此注解标注的通知方法在目标方法前、后都被执行
|@Before|前置通知，此注解标注的通知方法在目标方法前被执行
|@After|后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行
|@AfterReturning|返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行
|@AfterThowing|异常后通知，此注解标注的通知方法发生异常后执行

@Around环绕通知需要自己调用ProceedingJoinPoint.proceed()来让原始方法执行，其他通知不需要考虑目标方法执行
@Around环绕通知方法的返回值，必须执行为Object，来接收原始方法的返回值
@PointCut该注解的作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。【private：仅在当前切面类中引用该表达式；public：在其他外部切面类中也可以引用该表达式】

### 分模块设计
将项目按照功能拆分若干个子模块。方便项目的管理维护、扩展、模块间的相互调用、资源共享。【对项目先进行模块设计，在进行编码】

 1. 创建Maven模块，存放实体类
 2. 创建Maven模块，存放工具类
 3. 在项目中引用maven模块依赖

#### 继承

描述两个工程的关系，与java继承相似，子工程可以继承父工程的配置信息，常见于依赖关系的传递

作用：简化依赖配置、统一管理依赖

实现

```bash
<parent>...</parent>
```

 1. 创建Maven模块，该工程为父工程，设置打包方式pom（默认jar）

```xml
	<!-- 添加依赖 -->
 	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.hui</groupId>
    <artifactId>hui-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- 设置打包方式pom -->
    <packaging>pom</packaging>
```

 2. 在子工程的pom.xml文件中，配置继承关系。


```xml
	<!-- 修改依赖 -->
 	 <parent>
        <groupId>com.hui</groupId>
        <artifactId>hui-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../../hui-parent/hui-parent/pom.xml</relativePath>
    </parent>
    <!-- 删除重复配置 -->
    <groupId>com.hui</groupId>
```

 3. 在父工程中配置各个工程共有的依赖（子工程会自动继承父工程的依赖）

		若父子工程都配置同一个依赖不同版本，以子工程为准

**版本锁定**

在Maven中，可以在父工程的pom文件中通过dependencyManagement来统一管理依赖版本
		
		子工程引入依赖时，无需指定<version>版本号，父工程统一管理。变更依赖版本，只需在父工程中统一变更
		dependencies与dependencyManagement区别
		1、dependencies是直接依赖，在父工程配置了依赖，子工程会直接继承下来
		2、dependencyManagement是统一管理依赖版本，不会直接依赖，还需要在子工程引入所需依赖（无需指定版本）
		自定义属性/引用属性

```xml
 	<!--父工程-->
 	<properties>
        <!-- 自定义属性/引用属性-->
        <lombok.version>1.18.30</lombok.version>
        <jjwt.version>0.9.1</jjwt.version>
    </properties>
    <!--统一管理依赖版本-->
     <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </dependency>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>${jjwt.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

 <!--公共依赖-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>


```

|类型|说明
|:-|:-|
|jar|普通模块打包，Springboot项目基本都是jar包（内嵌Tomcat运行）
|war|普通web程序打包，需要部署在外部的Tomcat服务器运行
pom|父工程或聚合工程，该模块不写代码，仅进行依赖管理

### 聚合
[maven基础](https://blog.csdn.net/weixin_56478633/article/details/135308924?csdn_share_tail=%7B%22type%22:%22blog%22,%22rType%22:%22article%22,%22rId%22:%22135308924%22,%22source%22:%22weixin_56478633%22%7D)

聚合：将多个模块组织称一个整体，同时进行项目的构建
聚合工程：一个不具有业务功能的“空”工程（有且仅有一个pom文件）
作用：快速构建项目（无需根据依赖关系手动构建，直接在聚合工程上构建即可）

Maven中通过modules设置当前聚合工程所包含的子模块名称

**聚合工程中所包含的模块，在构建时，会自动根据模块的依赖关系设置构建顺序，与聚合工程中模块的配置书写位置无关**

```xml
 <!--聚合其他模块-->
    <modules>
        <module>其他模块的pom.xml路径</module>
    </modules>
```


**继承与聚合**

 - 作用：聚合用于快速构建项目，继承用于简化依赖配置、统一依赖管理
 - 相同点：聚合与集成的pom.xml文件打包方式均为pom，可以将两种关系制作到同一个pom文件中【聚合与集成均属于设计型模块，并无实际的模块内容】
 - 不同点：聚合是聚合工程中的配置关系，聚合可以感知到参与聚合的模块有哪些；继承是子模块中的配置关系，父模块无法感知哪些子模块继承了自己

### Springboot配置文件
优先级：application.yaml < application.yml【主流】< application.properties < java系统属性【-Dserver.port = 9000】< 命令行参数【--server.port=10000】

```打包指令
// 插件Spring-boot-maven-plugin【创建项目，默认存在】
java -Dserver.port = 9000 -jar xxx.jar --server.port=10000
```

### 私服
特殊的远程仓库，架设在局域网内的仓库服务，用来代理于外部的中央仓库，用于解决团队内部的资源共享与资源同步问题

依赖查找顺序：本地仓库>私服>中央仓库


**资源上传与下载**
>项目版本
1、SNAPSHOT（快照版本）：功能不稳定、尚处于开发者的版本，即快照版本，存储在私服的SNAPSHOT仓库中。
2、RELEASE（发行版本）：功能趋于稳定、当前更新停止，可以用于发行的版本，存储在私服中的RELEASE仓库中。

别人给你的
访问私服：https://192.168.xxx.xxx
访问密码：xxxxxx

在Maven的settings.xml配置文件中，做如下配置

1. 找到servers标签中，配置访问私服的个人凭证（访问的用户名和密码）

```xml
<server>
    <id>maven-releases</id>
    <username>admin</username>
    <password>123456</password>
  </server>
   <server>
    <id>maven-snapshots</id>
    <username>admin</username>
    <password>123456</password>
  </server>
```

2. 找到mirrors中配置私服的连接地址（之前配置的阿里云，需要直接替换掉）

```xml
 	<mirror>
        <id>maven-public</id>
        <url>https://192.168.xxx.xxx:8081/repository/public</url>
        <mirrorOf>*</mirrorOf>
    </mirror>
```

3. 找到profile中，添加如下配置，来指定快照版本的依赖，依然允许使用


```xml
 <profile>
      <id>allow-snapshots</id>
        <activation>
          <activeByDefault>true</activeByDefault>
        </activation>
        <repositories>
          <repository>
            <id>maven-public</id>
            <url>https://192.168.xxx.xxx:8081/repository/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
          </repository>
        </repositories>
    </profile>
```
4. IDEA的Maven工程的pom.xml文件中配置上传（发布）地址

```xml
 <distributionManagement>
  		<!--releases版本的发布地址-->
        <repository>
            <id>maven-releases</id> <!--id需要与第一步（servers配置）的id保持一致-->
            <url>https://192.168.xxx.xxx:8081/repository/maven-releases</url>
        </repository>
        <!--snapshots版本的发布地址-->
        <snapshotRepository>
            <id>maven-snapshots</id> 
            <url>https://192.168.xxx.xxx:8081/repository/maven-snapshots</url>
        </snapshotRepository>
    </distributionManagement>
```

5. 发布项目，直接运行deploy生命周期即可（发布时，建议跳过test单元测试）

#### 本地私服配置
[文档](https://help.sonatype.com/en/sonatype-nexus-repository.html)
1. 解压：apache-maven-nexus.zip [下载地址](https://www.sonatype.com/products/sonatype-nexus-oss-download)
2. 进入目录：apache-maven-nexus/xxx/bin 打开cmd 
3. 运行安装服务---> (nexus.exe /install) 和 启动服务--->(nexus.exe /start)命令
![cmd](https://img-blog.csdnimg.cn/direct/ed43488731c240c4b2683e9e853cc060.png)

```bash
nexus.exe /install 安装服务
nexus.exe /start 启动服务
nexus.exe /run 带控制台的启动服务
nexus.exe /stop 停止服务
nexus.exe /uninstall 卸载服务
```

4. 访问服务：localhost:8081
5. 初始密码：D:\java\nexus\windows\sonatype-work\nexus3下的admin.pawweord

### 获取bean
默认情况下，spring项目启动时，会把bean都创建好放在IOC容器中【还会收到作用域及延迟初始化影响，这里主要针对于默认的单例非延迟加载的bean而言】，如果想要主动获取这些bean，可以通过如下方式：

 - 根据name获取bean：object getBean(String name)
 - 根据类型获取bean：<T> T getBean(Class<T> requiredType)
 - 根据name获取bean（带类型转换）：<T> T getBean(String name,Class<T> requiredType)

|作用域|说明
|:-|:-|
|singleton|容器内同名称的bean只有一个实例（单例））默认
|prototype|每次使用该bean时会创建新的实例（非单例）
|request|每个请求范围内会创建新的实例（web环境）
|session|每个会话范围范围内会创建新的实例（web环境）
|application|每个应用范围内会创建新的实例（web环境）


## HTTP
Hyper Text Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间数据传输的规则

#### 特点

 - 基于TCP协议：面向连接、安全
 - 基于请求-响应模型的，一次请求对应一次响应
 - HTTP协议是无状态的协议，对于事务处理没有记忆能力。每次请求-响应都是独立的【多次请求间不能共享数据，速度快】
 - GET请求：请求参数在请求行（请求方式、资源路径、协议）中，没有请求体，限制请求大小
 - POST请求：请求参数在请求体中，POST请求没有大小限制


#### HTTP-请求数据格式【请求头】
>host：请求主机名
>User-agent：浏览器版本
>accept：表示浏览器能接受的资源类型
>accept-language：浏览器偏好的语言
>accept-encoding：浏览器可以支持的压缩类型
>content-type：请求主体的数据类型
>content-length：请求主题的大小（单位：字节）


#### HTTP-请求响应格式
>响应行：第一行（协议，状态码，描述）
>响应头
>content-type：请求响应内容类型
>content-length：请求响应内容的大小（单位：字节）
>content-encoding：响应压缩算法
>cache-control：客户端应如何缓存
>set-cookie：告诉浏览器为当前页面所在的域设置cookie
>响应体：存放响应数据
>[状态码](https://cloud.tencent.com/developer/chapter/13553)

## Web服务器

 1. web服务器是一个软件程序，对HTTP协议的操作进行封装，简化web程序开发，主要功能是提供网上信息浏览服务。
 2. 部署web项目，对外提供网上信息浏览服务

## Tomcat
[官网](https://tomcat.apache.org/)

 1. 一个轻量级的web服务器，支持servlet、jsp等少量JavaEE规范。
 2. 也称为web容器、servlet容器。
 3. servlet程序需要依赖于Tomcat。

基本使用

> 下载-安装-卸载（安装的目录）-启动【双击\bin\startup.bat】-关闭【bin\shutdown.bat或者直接关闭】
> 部署：应用复制到webapps目录
> 修改配置文件conf/logging.properties
> 中文乱码 java.util.logging.ConseleHandler.encoding = UTF-8（GBK）
> 启动窗口一闪而过：检查JAVA_HOME环境变量是否配置正确
> 端口号冲突

修改端口号conf/server.xml
HTTP协议默认端口号为80，访问可以不用输入端口号
```xml
 <Connector  port="8080" />
```

## 后端项目搭建
1、准备数据库表
2、创建springboot工程，引入对应的起步依赖（web、mybatis、mysql驱动、lombok）
3、配置文件application.properties中引入mybatis的配置信息，准备对应的实体类
4、准备对应的Mapper、Service（接口、实现类）、Controller基础结构

### 开发规范-restful
REST(Representational State Transfer)，表达性状态转换，它是一种软件架构风格。

```js
// 传统风格：get/post
http://localhost:8080/user/get?id=1  //get
http://localhost:8080/user/save      // post
http://localhost:8080/user/update    // post
http://localhost:8080/user/del?id=1  // get
// rest风格：get/post/put/delete
http://localhost:8080/users/1 // get
http://localhost:8080/users // post
http://localhost:8080/users //put
http://localhost:8080/users/1  // delete
```

### 统一响应结果

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code; // 请求码 200成功 201失败
    private String msg; // 提示信息
    private Object data; // 数据

    /**
     * 请求成功方法
     * @param data 返回数据
     * @return
     */
    public static Result success(Object data){
        return new Result(200,"success",data);
    }

    public static Result success(){
        return new Result(200,"success",null);
    }

    /**
     * 请求失败方法
     * @param msg 返回失败的提示信息
     * @return
     */
    public static Result error(String msg){
        return new Result(201,"error",msg);
    }
}

```
