package com.guilherme.AppRH.config;

import com.guilherme.AppRH.Service.UsuarioService;
import com.guilherme.AppRH.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.POST,"/usuario/**").permitAll();
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers("/error/**").permitAll();
                    authorize.requestMatchers("*/actuator/**").permitAll();
                    authorize.anyRequest().authenticated();

                } )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);

    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService service){
//        UserDetails user1 = User.builder()
//                .username("Guilherme")
//                .password(encoder.encode("1234"))
//                .roles("admin")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("Monte")
//                .password(encoder.encode("1234"))
//                .roles("User")
//                .build();
        return new CustomUserDetailsService(service);
    }
}
