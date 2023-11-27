package com.woofnmeow.wnm_project_back.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrincipalUserDetailsService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId  = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = null;
        String nameAttributeKey = null;
        switch (registrationId) {
            case "naver":
                attributes = (Map<String, Object>) oAuth2User.getAttributes().get("response");
                nameAttributeKey = "id";
                attributes.put("provider", registrationId);
                break;
            case "kakao":
                attributes = new HashMap<>();
                attributes.putAll(oAuth2User.getAttributes());
                nameAttributeKey = "id";
                attributes.put("provider", registrationId);
                break;
        }

        return new DefaultOAuth2User(new ArrayList<>(), attributes, nameAttributeKey);
    }
}
