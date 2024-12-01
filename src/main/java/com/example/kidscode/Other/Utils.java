package com.example.kidscode.Other;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Utils {

    public SimpleGrantedAuthority getRoleGrantedAuthority(String role){
        return new SimpleGrantedAuthority("ROLE_"+role.toUpperCase());
    }
}
