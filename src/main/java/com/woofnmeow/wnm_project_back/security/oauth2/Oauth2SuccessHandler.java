package com.woofnmeow.wnm_project_back.security.oauth2;

import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.jwt.JwtProvider;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import com.woofnmeow.wnm_project_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // !!
        OAuth2User principalUser = (PrincipalUser) authentication.getPrincipal();
        String oauth2Id = principalUser.getAttributes().get("id").toString();
        //
        User user = userMapper.findUserByOauth2Id(oauth2Id);
        
        if(user == null) {
            String id = principalUser.getAttributes().get("id").toString();
            String provider = principalUser.getAttributes().get("provider").toString();
            response.sendRedirect("http//localhost:3000/api/auth/signup" +
                    "?oauth2Id" + id +
                    "&provider" + provider);
        }

        // 권한 넣을려면 principal필요?
        // getAuthority로 권한 안널을거면 Principal객체로 만들 필요 없음? 
        // -> userDetails의 메소드를 구현하는게 아니라 OAuth2User의 메소드를 구현하면서 정보를 넣어줘야 함
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());

        String accessToken = jwtProvider.createToken(authenticationToken);
        response.sendRedirect("http://localhost:3000/api/auth/signin" +
                "?token=" + URLEncoder.encode(accessToken, "UTF-8"));
    }
}
