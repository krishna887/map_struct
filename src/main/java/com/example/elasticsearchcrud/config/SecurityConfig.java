package com.example.elasticsearchcrud.config;

import com.example.elasticsearchcrud.jwt.AuthEntryPointJwt;
import com.example.elasticsearchcrud.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final  DataSource dataSource;
  private final AuthEntryPointJwt unauthorizedHandler;
  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter(){
      return new AuthTokenFilter();
  }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      return   http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/signin").permitAll()
                        .anyRequest().authenticated())
              .exceptionHandling(exception-> exception.authenticationEntryPoint(unauthorizedHandler))
              .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)

//                .httpBasic(withDefaults())
//                .formLogin(withDefaults())
              .headers(headers-> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .build();
    }
@Bean
public UserDetailsService userDetailsService(DataSource dataSource){
      return new JdbcUserDetailsManager(dataSource);
}
    @Bean
    public CommandLineRunner initDate(UserDetailsService userDetailsService){
      return args -> {
          JdbcUserDetailsManager manager= (JdbcUserDetailsManager) userDetailsService;
          UserDetails user1 = User
                  .withUsername("krishna")
                  .password(passwordEncoder().encode("krishna"))
                  .roles("USER").build();

          UserDetails admin1 = User
                  .withUsername("roshan")
                  .password(passwordEncoder().encode("roshan"))
                  .roles("ADMIN").build();
          JdbcUserDetailsManager userDetailsManager= new JdbcUserDetailsManager(dataSource);
          userDetailsManager.createUser(user1);
          userDetailsManager.createUser(admin1);
      };
    }




//        return new InMemoryUserDetailsManager(user1, admin1);

    private final AuthenticationConfiguration authConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
