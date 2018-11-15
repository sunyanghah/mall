package com.sy.mall.zuul.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by dell on 2018/10/29.
 * @author dell
 */
@Data
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
