package com.woofnmeow.wnm_project_back.security;

import com.woofnmeow.wnm_project_back.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalUser extends DefaultOAuth2User {

    // oAuth2 로그인 후 추가로 받은 정보를 가져올 때 필요???
    @Getter
    private User user;

    public PrincipalUser(User user, Map<String, Object> attributes, String nameAttributeKey) {
        super(null, attributes, nameAttributeKey);
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
        return authorities;
    }

    @Override
    public String getName() {
        // 소셜 로그인 마다 id가 다른데 이거 수정 해야 되는거 같은데?
        if(user.getProvider() == "google") {
            return super.getAttributes().get("sub").toString();
        } else {
            return super.getAttributes().get("id").toString();
        }
    }
}
