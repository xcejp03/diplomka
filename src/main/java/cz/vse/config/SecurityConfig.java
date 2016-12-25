package cz.vse.config;

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
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username,password, enabled from person where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_role where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers( "/login", "/home", "/index", "/resources/**", "/static/**", "/", "/test/thym", "/thyme", "../script.js", "/script.js", "/css/**",
                        "/resources/static/**", "/resources/static", "/resources/static/css", "/resources/static/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")
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
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//            /*auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
//            auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//            auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");*/
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
///*
//        http.authorizeRequests()
//                .antMatchers("/admin*/
///**").access("hasRole('ROLE_ADMIN')")
// .antMatchers("/dba*/
///**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
// .and().formLogin();
// */
//        http
//                .formLogin()
//                .loginPage("/login.html")
//                .failureUrl("/login-error.html")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/index.html");
//
////        http.authorizeRequests().antMatchers("/admin/**")
////                .access("hasRole('ROLE_ADMIN')").and().formLogin()
////                .loginPage("/login").failureUrl("/login?error")
////                .usernameParameter("username")
////                .passwordParameter("password")
////                .and().logout().logoutSuccessUrl("/login?logout")
////                .and().csrf()
////                .and().exceptionHandling().accessDeniedPage("/403");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
//}