package com.example.elasticsearchcrud.config;

import com.example.elasticsearchcrud.security.JwtAuthEntryPoint;
import com.example.elasticsearchcrud.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthEntryPoint authEntryPoint;
    private final JwtAuthenticationFilter filter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(req-> req.requestMatchers("/register/**","/login/**")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET).hasAnyAuthority("admin")
//                            .requestMatchers(HttpMethod.PUT,"/hi").hasAnyAuthority(USER)
                            .anyRequest()
                            .authenticated())
//                    .httpBasic(withDefaults())
//                    .userDetailsService(userDetailsService)
                    .sessionManagement(session->session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .exceptionHandling(exception->exception.authenticationEntryPoint(authEntryPoint))
                    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                    .build();

        }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
