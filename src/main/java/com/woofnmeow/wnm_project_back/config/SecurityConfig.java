package com.woofnmeow.wnm_project_back.config;

import com.woofnmeow.wnm_project_back.security.oauth2.Oauth2SuccessHandler;
import com.woofnmeow.wnm_project_back.service.PrincipalUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final PrincipalUserDetailsService principalUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .oauth2Login()
                .successHandler(oauth2SuccessHandler)
                .userInfoEndpoint()
                .userService(principalUserDetailsService);

    }
}
