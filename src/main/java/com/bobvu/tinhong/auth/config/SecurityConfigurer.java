package com.bobvu.tinhong.auth.config;

import com.bobvu.tinhong.auth.handler.RestAccessDeniedHandler;
import com.bobvu.tinhong.auth.handler.RestAuthenticationEntryPoint;
import com.bobvu.tinhong.auth.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    private final RestAccessDeniedHandler accessDeniedHandler;

    private final RestAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/", "/csrf", "/authentication/**", "/swagger-resources/**", "/swagger-ui.html",
                        "/v2/api-docs", "/webjars/**", "/resources/**", "/index.html**", "/test**", "/socket.io/**", "/client.html**", "/client2.html**", "/uploads/**")
                .permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/host/**").hasAnyAuthority("HOST", "ADMIN")
                .antMatchers("/student/**").hasAuthority("STUDENT")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);



    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}