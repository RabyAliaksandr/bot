package com.raby.citybot.security.configuration;


import com.raby.citybot.security.CustomUserDetailsService;
import com.raby.citybot.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String SIGN_IN_URL = "/auth/signin";
    private static final String SIGN_UP_URL = "/auth/signup";
    private CustomUserDetailsService customUserDetailsService;
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                             CustomUserDetailsService customUserDetailsService,
                             JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers(SIGN_IN_URL, SIGN_UP_URL)
                .permitAll()
//                .antMatchers(HttpMethod.DELETE, "/**")
////                .hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.POST, "/**")
//                .hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.PUT, "/**")
//                .hasRole(ROLE_ADMIN)
                .anyRequest()
                .authenticated().and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
