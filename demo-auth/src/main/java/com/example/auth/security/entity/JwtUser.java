package com.example.auth.security.entity;

import com.example.auth.user.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p> @Title JwtUser
 * <p> @Description TODO
 *
 * @author zhj
 * @date 2020/12/22 13:53
 */
@Data
public class JwtUser implements UserDetails {

    private SysUser user;

    public JwtUser(SysUser user) {
        this.user = user;
    }

    // authorities 用于存储用户的角色
//    public JwtUser(Collection<GrantedAuthority> authorities, String password, String username, String phone) {
//        this.authorities = authorities;
//        this.password = password;
//        this.username = username;
//        this.phone = phone;
//    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isValid();
    }

}