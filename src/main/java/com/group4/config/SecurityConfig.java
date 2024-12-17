package com.group4.config;


import com.group4.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private NguoiDungService nguoiDungService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(nguoiDungService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successEvent() {
        return event -> {
            System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent() {
        return event -> {
            System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure()
                .and()
                .csrf().disable()
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
		                     	// Cho phép truy cập tài nguyên tĩnh ngoài admin
		                        .requestMatchers("/static/shopz/**","/static/vendor/**").permitAll()
		                        //
		                        .requestMatchers("/dangky","/shop","/contact",
                                    "/home", "/oauth/**", "/login/**", "/login-google", "/", "/register", "/save", "re-send", "/shopz/**", "/sendMail", "/loginError",
                                    "/api/**", "/save-new-password", "/send-otp-recover", "/confirm-otp-recover", "/forgot-password", "/savedangky","/detail/**"
                                ).permitAll()
		                        .requestMatchers("/cart").hasAnyAuthority("ROLE_CUST","ROLE_DIRE","ROLE_STAF")
                                .requestMatchers("/admin/**", "/admin", "/static/**").hasAnyAuthority("ROLE_DIRE","ROLE_STAF")
                                .anyRequest().authenticated()
                )
                .formLogin().permitAll()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/loginError")
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/login");
                })
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                });

        return http.build();
    }



}

