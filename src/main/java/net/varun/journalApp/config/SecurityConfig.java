package net.varun.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable()) // Disable CSRF for API endpoints
                                .authorizeRequests(requests -> requests
                                                // .antMatchers("/users/**").permitAll() // Allow user registration
                                                // without auth
                                                .antMatchers(
                                                                "/journal/**", "/users/**")
                                                .authenticated()
                                                .antMatchers(
                                                                "/admin/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .httpBasic(withDefaults())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless
                                                                                                          // API

                http.csrf(x -> x.disable());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
