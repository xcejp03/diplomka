package cz.vse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by pcejk on 25.12.2016.
 */
@Service
public class UserDetailServiceImpl {

    @Autowired
    DataSource dataSource;

    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource);
        jdbcImpl.setUsersByUsernameQuery("select username,password, enabled from person where username=?");

        jdbcImpl.setAuthoritiesByUsernameQuery("select username, role from user_role where username=?");
        return jdbcImpl;
    }

}
