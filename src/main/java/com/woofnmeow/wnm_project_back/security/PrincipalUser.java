package com.woofnmeow.wnm_project_back.security;

import com.woofnmeow.wnm_project_back.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalUser implements OAuth2User {

    // oAuth2 로그인 후 추가로 받은 정보를 가져올 때 필요???
    @Getter
    private User user;
    private Map<String, Object> attributes;

    public PrincipalUser(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
        return authorities;
    }

    @Override
    public String getName() {
        return attributes.get("id").toString();
    }
}
