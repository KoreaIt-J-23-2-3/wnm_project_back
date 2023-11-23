package com.woofnmeow.wnm_project_back.config;

import com.woofnmeow.wnm_project_back.filter.JwtAuthenticationFilter;
import com.woofnmeow.wnm_project_back.security.PrincipalEntryPoint;
import com.woofnmeow.wnm_project_back.security.oauth2.Oauth2SuccessHandler;
import com.woofnmeow.wnm_project_back.service.PrincipalUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final PrincipalUserDetailsService principalUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PrincipalEntryPoint principalEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/product/**","/api/products/**", "/api/reviews/product/**", "/api/auth/**")
                .permitAll()
                .antMatchers("/api/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(principalEntryPoint)
                .and()
                .oauth2Login()
                .successHandler(oauth2SuccessHandler)
                .userInfoEndpoint()
                .userService(principalUserDetailsService);
    }


}
