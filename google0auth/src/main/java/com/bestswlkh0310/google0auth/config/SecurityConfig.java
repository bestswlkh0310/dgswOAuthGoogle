package com.bestswlkh0310.google0auth.config;

import com.bestswlkh0310.google0auth.auth.AccessDeniedHandler;
import com.bestswlkh0310.google0auth.auth.AuthenticationEntryPoint;
import com.bestswlkh0310.google0auth.auth.oauth.PrincipalOauth2UserService;
import com.bestswlkh0310.google0auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority(UserRole.ROLE_ADMIN.name())
                    .antMatchers("/info").hasAuthority(UserRole.ROLE_USER.name())
                    .anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                    .oauth2Login()
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint()
                .userService(principalOauth2UserService);
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint())
                .accessDeniedHandler(new AccessDeniedHandler());

        return http.build();
    }
}
