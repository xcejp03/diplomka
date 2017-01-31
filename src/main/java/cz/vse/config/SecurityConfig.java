package cz.vse.config;

import cz.vse.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by pcejk on 20.12.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/login", "/home", "/", "/thyme", "/person/prihlasit", "/script.js", "/style.css", "/admin/**", "/person/**").permitAll()
                .antMatchers("/test/thym", "/font-awesome/**",  "/index", "/person/create", "/sweetalert/**",  "/datatable/**",  "/jquery/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login-error")
                .successHandler(customAuthenticationSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}