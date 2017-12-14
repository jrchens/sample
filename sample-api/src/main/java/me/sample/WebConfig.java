package me.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "me.sample.**.*.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("add cors mapping {}", "/**");
        registry.addMapping("/**");
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver() {
        return new StandardServletMultipartResolver();
    }

    // @Bean
    // public MessageSource messageSource() {
    // ResourceBundleMessageSource messageSource = new
    // ResourceBundleMessageSource();
    // messageSource.setBasename("messages");
    // return messageSource;
    // }

//    <!-- The filter-name matches name of a 'shiroFilter' bean inside applicationContext.xml -->
//    <filter>
//        <filter-name>shiroFilter</filter-name>
//        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
//        <init-param>
//            <param-name>targetFilterLifecycle</param-name>
//            <param-value>true</param-value>
//        </init-param>
//    </filter>
//
//    ...
//
//    <!-- Make sure any request you want accessible to Shiro is filtered. /* catches all -->
//    <!-- requests.  Usually this filter mapping is defined first (before all others) to -->
//    <!-- ensure that Shiro works in subsequent filters in the filter chain:             -->
//    <filter-mapping>
//        <filter-name>shiroFilter</filter-name>
//        <url-pattern>/*</url-pattern>
//    </filter-mapping>
}
