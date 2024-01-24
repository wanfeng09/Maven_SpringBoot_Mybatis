package com.hui.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    /*
    * 优点：HTTP协议中支持的技术
    * 缺点：移动端app无法使用cookie
    * 不安全，用户可以自己禁用cookie
    * cookie不能跨域【协议、ip/域名、端口】
    *
    * */
    @GetMapping("setCookie")
    public Result setCookie(HttpServletResponse response){
        // 设置cookie
        response.addCookie(new Cookie("login_username","hui"));
        return Result.success();
    }

    @GetMapping("getCookie")
    public Result getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies(); // 获取所有的cookie
        for (Cookie c:cookies){
            if(c.getName().equals("login_username")){
                System.out.println("login_username--->" + c.getValue());
            }
        }
        return Result.success();
    }

    /*
    * 优点：存储在服务端，安全
    * 缺点：服务器集群环境下无法直接使用session
    * cookie的缺点
    *
    * */

    @GetMapping("setSession")
    public Result setSession(HttpSession session){
        session.setAttribute("loginUser","hui2");
        return Result.success();
    }

    @GetMapping("getSession")
    public Result getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");
        System.out.println("user--->" + user);
        return Result.success();
    }

    /*
    * 令牌技术JWT（JSON web token）
    * 定义了一种简洁的，自包含的格式，用于在通信双方以JSON数据格式安全的传输信息。由于数字签名的存在，这些信息是可靠的
    * 组成
    * 第一部分：header头，记录令牌类型、签名算法等。
    * 第二部分：有效载荷，携带一些自定义信息、默认信息等。
    * 第三部分：签名，防止token被篡改、确保安全性。
    *
    * 支持pc/移动端
    * 解决集群环境下的认证问题
    * 减轻服务器存储压力
    *
    * */

    @GetMapping("setToken")
    public Result setJWT(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("userName","hui3");
        // 签名算法+载荷+有效期1h
        // 密钥字符串至少4位
        // jdk17 【jjwt 和 jaxb-api依赖】
        String str = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"huiSecret")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        return Result.success(str);
    }

    /*
    *
    * 解析JWT
    * */
    @GetMapping("getToken")
    public Result getJWT(String token){
        Claims claims = Jwts.parser().setSigningKey("huiSecret")
                .parseClaimsJws(token)
                .getBody();
       return Result.success(claims);
    }
}
