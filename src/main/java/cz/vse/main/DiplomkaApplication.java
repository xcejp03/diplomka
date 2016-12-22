package cz.vse.main;

import cz.vse.config.ConfigConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = ConfigConstants.COMPONENT_SCAN_BASE_PACKAGE)
@EnableAutoConfiguration
@EnableAsync
@EnableAspectJAutoProxy
public class DiplomkaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DiplomkaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(DiplomkaApplication.class);
    }



}
