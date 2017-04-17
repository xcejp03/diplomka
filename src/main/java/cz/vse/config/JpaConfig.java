package cz.vse.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by pcejka on 30.11.2016.
 */

@Configuration
@EnableJpaRepositories(basePackages = ConfigConstants.REPOSITORIES_PACKAGE)
@EntityScan(basePackages = ConfigConstants.ENTITIES_PACKAGE)
@EnableTransactionManagement
public class JpaConfig {

}

