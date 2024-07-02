package com.api.Agencia.Configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()  
                )
                .logout((logout) -> logout.permitAll());  // Permitir logout para todos

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("lucas")
                .password("password")
                .roles("FORBIDDEN")  // Define uma role específica para restringir acesso
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER")  // Define uma role para acesso permitido
                .build());
        return manager;
    }
}