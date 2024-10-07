package com.travelmate.config;

import com.travelmate.commons.service.port.SystemHolder;
import com.travelmate.domain.auth.filter.JwtAuthenticationFilter;
import com.travelmate.domain.auth.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // Role to enable Spring Security settings
public class SecurityConfig {

    private final SystemHolder systemHolder;
    private final TokenProvider tokenProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Disables Cross-Site Request Forgery (CSRF) protection
                .formLogin(AbstractHttpConfigurer::disable) // Disable the default login page (Form-based login)
                .httpBasic(AbstractHttpConfigurer::disable) // Disables HTTP Basic Authentication
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource())) // CORS configuration
                .sessionManagement( // 세션을 생성하거나 사용하지 않겠다는 뜻, JWT(JSON Web Token)와 같은 토큰 기반 인증을 사용할 때 이 설정을 사용
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/v1/auth/withdrawal")
                                .authenticated()
                                .requestMatchers("/api/v1/user/**")
                                .authenticated()
                                .requestMatchers("/api/v1/course/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll() // All other requests need authentication
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
                .exceptionHandling(
                        exception ->
                                exception
                                        .authenticationEntryPoint(authenticationEntryPoint) // 인증되지 않은 사용자가 보호된 리소스에 접근하려 할 때 처리하는 동작을 정의하는 것
                                        .accessDeniedHandler(accessDeniedHandler)) // Spring Security에서 인가(Authorization) 실패가 발생했을 때 어떤 처리를 할지 정의하는 부분
                .build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, systemHolder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean for password encryption using BCrypt
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        //        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                        .requestMatchers("/images/**", "/js/**", "/webjars/**")
                        .requestMatchers(
                                // -- Swagger UI v3 (OpenAPI)
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/docs/openapi3.yaml",
                                "/docs/swagger",
                                "/docs/swagger-ui/**",

                                // Actuator
                                "/actuator/health/readiness",
                                "/actuator/health/liveness");
    }
}

