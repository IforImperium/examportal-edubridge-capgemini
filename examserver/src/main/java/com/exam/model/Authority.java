package com.exam.model;

import org.springframework.security.core.GrantedAuthority;
//GrantedAuthority interface provided by Spring Security.
public class Authority implements GrantedAuthority {
//	store the authority (role name)
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
