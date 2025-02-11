package space.nurik.preproject3.task_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import space.nurik.preproject3.task_2.services.UserService;

@Configuration
public class SecurityConfig {

    private final UserService userDetailsService;

    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/hello").permitAll() // Доступ к /user для USER и ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ к /user для USER и ADMIN
                        .requestMatchers("/**").hasRole("ADMIN")  // Доступ к /admin только для роли ADMIN
                        .anyRequest().authenticated() // Остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Указание страницы логина
                        .loginProcessingUrl("/process_login") // URL для обработки логина
                        .defaultSuccessUrl("/hello", true) // Куда перенаправлять после успешного входа
                        .failureUrl("/login?error=true") // Куда перенаправлять при ошибке
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessUrl("/login?logout") // Куда перенаправлять после выхода
                        .invalidateHttpSession(true) // Удаление сессии
                        .deleteCookies("JSESSIONID") // Удаление cookie сессии
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
