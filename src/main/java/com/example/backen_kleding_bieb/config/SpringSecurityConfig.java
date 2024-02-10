package com.example.backen_kleding_bieb.config;


import com.example.backen_kleding_bieb.filter.JwtRequestFilter;
import com.example.backen_kleding_bieb.service.CustomUserDetailsService;
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



    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;


    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter ,
                                PasswordEncoder passwordEncoder){






        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)

                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()


                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                //  a photo to a item
                .antMatchers(HttpMethod.POST, "/items/**").permitAll()

                .antMatchers(HttpMethod.POST, "/items").permitAll()
                .antMatchers(HttpMethod.GET, "/items").permitAll()
                .antMatchers(HttpMethod.GET, "/items/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/items/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/items/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/items/**").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/orders").permitAll()
                .antMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders").permitAll()
                .antMatchers(HttpMethod.PATCH, "/orders/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/orders/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/accounts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/accounts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/subscriptions").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/subscriptions/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/subscriptions").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/subscriptions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/subscriptions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/subscriptions/**").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/downloadAllFiles").permitAll()
                .antMatchers(HttpMethod.GET, "/files").permitAll()
                .antMatchers(HttpMethod.POST, "/upload").permitAll()





                ///////////// mail  /////////////////////////////////////////////////////////////
                .antMatchers(HttpMethod.POST,"/sendMail").hasRole("ADMIN")
                .antMatchers("/sendMailWithAttachment").hasRole("ADMIN")

                /////////// up and download   /////////////////////////////////////////////////
                .antMatchers("/upload/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/download/**").hasAnyRole("ADMIN", "USER")

                ///////////// authentication     ////////////////////////////////////////////////////
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()



                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

