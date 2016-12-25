//package cz.vse.config;
//
///**
// * Created by pcejk on 24.12.2016.
// */
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.nio.file.Files;
//
//    /**
//     * Created by gkatzioura on 9/2/16.
//     */
//    @Configuration
//    @Component
//    public class DataSourceConfig {
//
////        @Bean
////        public DataSource createDataSource() {
////
////            JdbcDataSource dataSource = new JdbcDataSource();
////            dataSource.setURL("jdbc:h2:"+System.getProperty("java.io.tmpdir")+"/database");
////
////            return dataSource;
////        }
//
//        @Bean(name = "dataSource")
//        public DriverManagerDataSource dataSource() {
//            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//            driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//            driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/diplomka");
//            driverManagerDataSource.setUsername("xcejp03");
//            driverManagerDataSource.setPassword("heslo");
//            return driverManagerDataSource;
//        }
//
//    }