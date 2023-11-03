package com.woofnmeow.wnm_project_back.jwt;

import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private final UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    public String createToken(Authentication authentication) {
        OAuth2User principalUser = (PrincipalUser) authentication.getPrincipal();
        String oauth2Id = principalUser.getAttributes().get("id").toString();

        // token에 추가로 받은 유저 정보를 넣으려면 찾아와야함
        User user = userMapper.findUserByOauth2Id(oauth2Id);

        Date date = new Date(new Date().getTime() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .setSubject("accessToken")
                .setExpiration(date)
                .claim("oauth2Id", oauth2Id)
                .claim("userId", user.getUserId())
                .claim("authority", principalUser.getAuthorities())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
