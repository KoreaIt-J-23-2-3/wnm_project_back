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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = authenticationToken.getPrincipal();


        String oauth2Id = oAuth2User.getName();
        String provider = oAuth2User.getAttribute("provider") == null ? "google" : oAuth2User.getAttribute("provider").toString();

        User user = userMapper.findUserByOauth2Id(oauth2Id);
        
        if(user == null) {
            response.sendRedirect("http://localhost:3000/auth/signup" +
                    "?oauth2Id=" + oauth2Id +
                    "&provider=" + provider);
            return;
        }
        PrincipalUser principalUser = new PrincipalUser(user, oAuth2User.getAttributes(), provider.equals("google") ? "sub" : "id");

        String accessToken = jwtProvider.createToken(principalUser);
        response.sendRedirect("http://localhost:3000/auth/oauth2/signin" +
                "?token=" + URLEncoder.encode(accessToken, "UTF-8"));
    }
}
