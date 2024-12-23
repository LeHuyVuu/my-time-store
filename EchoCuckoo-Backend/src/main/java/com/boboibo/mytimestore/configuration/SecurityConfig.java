package com.boboibo.mytimestore.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomJwtFilter customJwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Cấu hình CORS
                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu cần thiết

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(auth -> {
                    auth
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").permitAll()


                            .requestMatchers(HttpMethod.GET, "/api/v1/products").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/news").permitAll()
                            .requestMatchers("/api/v1/products/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/logout").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/refresh").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/products/search").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/products/getProduct").permitAll()
                            .requestMatchers(HttpMethod.GET,"api/v1/mail/sendEmail").permitAll()
                            .requestMatchers(HttpMethod.GET, "api/v1/payment/vn-pay" ).permitAll()
                            .requestMatchers(HttpMethod.GET, "api/v1/payment/vn-pay-callback").permitAll()
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                            .anyRequest().authenticated(); // Các yêu cầu khác cần xác thực
                });

        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Thay thế bằng domain cụ thể nếu cần
        configuration.addAllowedMethod("*"); // Thay thế bằng các phương thức cụ thể nếu cần
        configuration.addAllowedHeader("*"); // Thay thế bằng các tiêu đề cụ thể nếu cần

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
