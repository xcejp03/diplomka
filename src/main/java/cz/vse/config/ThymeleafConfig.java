//package cz.vse.config;
//
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
//import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//
//@Configuration
//@ConditionalOnClass({SpringTemplateEngine.class})
//@EnableConfigurationProperties({ThymeleafProperties.class})  //no sense rolling our own.
//@AutoConfigureAfter({WebMvcAutoConfiguration.class})
//public class ThymeleafConfig implements ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
////        resolver.setOrder(2147483642);
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//
//    @Bean
//    //made this @Bean (vs private in Thymeleaf migration docs ), otherwise MessageSource wasn't autowired.
//    public TemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    private ServletContextTemplateResolver templateResolver() {
//        final ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML5");
//        return resolver;
//    }
//
////    private ITemplateResolver templateResolver() {
////        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
////        resolver.setApplicationContext(applicationContext);
////        resolver.setPrefix("/WEB-INF/templates");
////        resolver.setSuffix(".html");
////        resolver.setTemplateMode("HTML");
////        resolver.setCacheable(false);
////        return resolver;
////    }
//
//
//
//
//
//}