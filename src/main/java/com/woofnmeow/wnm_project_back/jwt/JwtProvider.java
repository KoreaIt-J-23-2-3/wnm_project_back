package com.woofnmeow.wnm_project_back.jwt;

import com.woofnmeow.wnm_project_back.entity.User;
import com.woofnmeow.wnm_project_back.repository.UserMapper;
import com.woofnmeow.wnm_project_back.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final Key key;
    private final UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Autowired UserMapper userMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    public String createToken(PrincipalUser principalUser) {
        String oauth2Id = null;

        if(principalUser.getAttribute("sub") != null) {
            oauth2Id = principalUser.getAttributes().get("sub").toString();
        } else if(principalUser.getAttribute("id") != null) {
            oauth2Id = principalUser.getAttributes().get("id").toString();
        }

        System.out.println(principalUser.getAuthorities());

        // token에 추가로 받은 유저 정보를 넣으려면 찾아와야함
        User user = userMapper.findUserByOauth2Id(oauth2Id);
        System.out.println("oauth2id로 찾은 user의 id없음?" + user.getUserId());

        Date date = new Date(new Date().getTime() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .setSubject("accessToken")
                .setExpiration(date)
                .claim("oauth2Id", oauth2Id)
                .claim("userId", user.getUserId())
                .claim("authority", principalUser.getAuthorities())
                .claim("provider", principalUser.getAttribute("provider"))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getToken(String bearerToken) {
        if(!StringUtils.hasText(bearerToken)) {
            return null;
        }
        return bearerToken.substring("Bearer ".length());
    }

    public Claims getClaims(String jwtToken) {
        System.out.println("getClaims" + jwtToken);
        Claims claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public Authentication getAuthenticated(String jwtToken) {
        Claims claims = getClaims(jwtToken);
        if(claims == null) {
            return null;
        }

        User user = userMapper.findUserByUserId((Integer) claims.get("userId"));
        System.out.println("getAuthenticate에 user정보 찍힘? : " + user);

        if(user == null) {
            System.out.println("if는 도냐?");
            return null;
        }

        String provider = claims.get("provider").toString();
        int userId = (Integer) claims.get("userId");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("provider", provider);
        if(user.getProvider() == "google") {
            attributes.put("sub", userId);
        } else {
            // getName이 어디서 실행되는지는 못찾았는데 attributes에서 id를 반환해야되서 userId로 넣어놈
            attributes.put("id", userId);
        }

        PrincipalUser principalUser = new PrincipalUser(user, attributes, provider.equals("google") ? "sub" : "id");

        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }
}
