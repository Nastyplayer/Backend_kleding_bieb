package com.example.backen_kleding_bieb.config;


import KledingBib.demo.filter.JwtRequestFilter;
import KledingBib.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /*inject customUserDetailService en jwtRequestFilter--- DONE!!!*/

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;


    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter ,
                                PasswordEncoder passwordEncoder){






        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    // Authenticatie met customUserDetailsService en passwordEncoder


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)

                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//     }

    // Authorizatie met jwt

    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()


                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/items").permitAll()
                .requestMatchers(HttpMethod.GET, "/items/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/orders").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/accounts").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/subscriptions").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/subscriptions/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/downloadAllFiles").permitAll()
                .requestMatchers(HttpMethod.GET, "/files").permitAll()



                .requestMatchers(HttpMethod.POST, "/items").permitAll()
                .requestMatchers(HttpMethod.POST, "/upload").permitAll()
                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST, "/accounts").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/accounts/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/subscriptions").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/subscriptions/**").hasRole("ADMIN")


                .requestMatchers(HttpMethod.PUT, "/items/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/accounts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/subscriptions/**").hasRole("ADMIN")


                .requestMatchers(HttpMethod.DELETE, "/items/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/subscriptions/**").hasRole("ADMIN")


                //  a photo to a item
                .requestMatchers(HttpMethod.POST, "/items/**").hasAnyRole("ADMIN", "USER")

                // mail
                .requestMatchers("/sendMail").hasRole("ADMIN")
                .requestMatchers("/sendMailWithAttachment").hasRole("ADMIN")

                // up and download
                .requestMatchers("/upload/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/download/**").hasAnyRole("ADMIN", "USER")

                // authentication
                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()


                .anyRequest().denyAll()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

// .requestMatchers(HttpMethod.GET, "/items").hasAnyRole("ADMIN", "USER")
//  .requestMatchers(HttpMethod.POST, "/items").hasAnyRole("ADMIN", "USER")
