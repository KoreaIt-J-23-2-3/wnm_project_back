package com.woofnmeow.wnm_project_back.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class PrincipalUserDetailsService implements OAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // !!
//        // 소셜로그인 서비스를 제곤한 회사에 등록된 아이디
//        String registrationId  = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attribute = oAuth2User.getAttributes();
        Map<String, Object> response = (Map<String, Object>) attribute.get("response");
        String provider = userRequest.getClientRegistration().getClientName();
        response.put("provider", provider);

        return new DefaultOAuth2User(new ArrayList<>(), response, "id");
    }
}
