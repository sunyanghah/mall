package com.sy.mall.zuul.security;

import com.sy.mall.common.dto.zuul.OutResourcesDto;
import com.sy.mall.zuul.resources.mapper.ResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by dell on 2018/10/29.
 * @author dell
 */
public class JWTAuthenticationFilter extends GenericFilterBean{

    @Autowired
    private ResourcesMapper resourcesMapper;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        //认证
        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest)request);

        //鉴权 网关统一鉴权，如果需要各业务系统实现鉴权，则鉴权的具体实现下放
        if (null != authentication){

            List<String> userRoles = new ArrayList<>();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (null != authorities && authorities.size() > 0) {
                for (GrantedAuthority simpleGrantedAuthority : authorities) {
                    String authority = simpleGrantedAuthority.getAuthority();
                    userRoles.add(authority);
                }
            }
            String url = ((HttpServletRequest) request).getRequestURI();
            boolean flag = authorization(url,userRoles);
            if (!flag){
                throw new AccessDeniedException("无权限操作");
            }
        }

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }


    private boolean authorization(String url,List<String> userRoles){
        //获取所有需要鉴权的资源以及所属角色
        List<OutResourcesDto> resourcesDtos = resourcesMapper.getAllResource();
        //如果没有需要鉴权的资源，则直接允许访问
        if (null == resourcesDtos || resourcesDtos.size() == 0){
            return true;
        }

        boolean authFlag = false;

        //如果匹配到要访问的资源需要鉴权，则取出其所有允许访问此资源的角色
        List<String> authRoles = new ArrayList<>();
        for (OutResourcesDto outResourcesDto : resourcesDtos){
            List<String> resourcesList = outResourcesDto.getResourceList();
            if (resourcesList != null && resourcesList.size() > 0){
                for (String resources : resourcesList){
                    if(antPathMatcher.match(resources,url) ){
                        authFlag = true;
                        if (outResourcesDto.getRoleName() != null){
                            authRoles.add(outResourcesDto.getRoleName());
                        }
                    }
                }
            }
        }

        if (!authFlag){
            return true;
        }

        if (authRoles.size() > 0){
            //如果登录用户没有角色，且资源需要鉴权，则返回false
            if (null == userRoles || userRoles.size() == 0){
                return false;
            }

            //如果登录用户拥有的角色可以访问资源，则返回true
            for (String authRole : authRoles){
                for (String userRole : userRoles){
                    if (authRole.equals(userRole)){
                        return true;
                    }
                }
            }

        } else {
            //如果资源需要鉴权，且没有任何角色有权限，则返回false
            return false;
        }

        return false;
    }

}
