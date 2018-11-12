package com.sy.mall.zuul.security;

import com.alibaba.fastjson.JSON;
import com.sy.mall.common.platform.RP;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2018/10/29.
 * @author dell
 */
public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 1200000;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    // JWT生成方法
    static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> authorities) {

        StringBuilder authoritiesStr = new StringBuilder();
        if (null != authorities && authorities.size() > 0){
            for (GrantedAuthority grantedAuthority : authorities){
                authoritiesStr.append(grantedAuthority).append(",");
            }
        }
        if (authoritiesStr.length() > 0){
            authoritiesStr = authoritiesStr.deleteCharAt(authoritiesStr.length()-1);
        }

        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", authoritiesStr.toString())
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 body
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(JSON.toJSONString(RP.buildSuccess("登录成功",JWT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JWT验证方法
    static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
//            Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) claims.get("authorities");
            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }

}
