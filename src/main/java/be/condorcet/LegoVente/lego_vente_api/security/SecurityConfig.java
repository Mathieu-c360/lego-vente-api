package be.condorcet.LegoVente.lego_vente_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            
            .httpBasic(Customizer.withDefaults())
            
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/utilisateur/add").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/utilisateur/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/article/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/theme/**").permitAll()


                .requestMatchers(HttpMethod.POST, "/api/article/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/article/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/theme/**").hasRole("ADMIN")

     
                .requestMatchers("/api/commande/**").authenticated()


                .anyRequest().authenticated()
            );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}