package me.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
//@PropertySources(value = { @PropertySource(value = { "classpath:jdbc.properties" }) })
//@ComponentScan(basePackages = { "me.sample.**.*.service.impl" })
//@MapperScan("me.sample.**.*.mapper")
public class AppConfig {

    // @Value("#{jdbc.url}")
    // private String url;
    // @Value("#{jdbc.username}")
    // private String username;
    // @Value("#{jdbc.password}")
    // private String password;
    // @Value("#{jdbc.driverClassName}")
    // private String driverClassName;

    @Autowired
    private Environment env;
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public DataSource dataSource() {
        String url = env.getProperty("jdbc.url");
        String username = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");
        String driverClassName = env.getProperty("jdbc.driverClassName");
        logger.info("url:{}, username:{}, password:{}, driverClassName:{}", url, username, password, driverClassName);

        DriverManagerDataSource ds = new DriverManagerDataSource(url, username, password);
        ds.setDriverClassName(driverClassName);
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());

        Properties prop = new Properties();
        com.github.pagehelper.PageInterceptor pi = new com.github.pagehelper.PageInterceptor();
        prop.setProperty("helperDialect", "mysql");
        prop.setProperty("reasonable", "true");
        prop.setProperty("supportMethodsArguments", "true");
        pi.setProperties(prop);

        sessionFactory.setPlugins(new Interceptor[] { pi });
        return sessionFactory.getObject();
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/login.html");
        
        Map<String, String> filterChainDefinitionMapping = new HashMap<>();
        filterChainDefinitionMapping.put("/login.html", "anon");
        filterChainDefinitionMapping.put("/**", "authc");
        
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        
        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new FormAuthenticationFilter());
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/login?logout");
//        filters.put("logout", logoutFilter);
//        filters.put("roles", new RolesAuthorizationFilter());
//        filters.put("user", new UserFilter());
        
        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jdbcRealm());
        // securityManager.setSessionMode("native");
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean(name = "jdbcRealm")
    public JdbcRealm jdbcRealm() {
        JdbcRealm jdbcRealm = new  JdbcRealm();
        jdbcRealm.setDataSource(dataSource());
        jdbcRealm.setAuthenticationQuery("select password from sys_user where username = ?");
        jdbcRealm.setUserRolesQuery("select rolename from sys_user_role where username = ?");
        jdbcRealm.setPermissionsQuery("select permission_code from sys_role_permission where rolename = ?");
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }
    // @Bean
    // public ObjectFactory objectFactory(){
    // return new ObjectFactory();
    // }

    // @Bean
    // public Jaxb2Marshaller jaxb2Marshaller() {
    // Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    // jaxb2Marshaller.setPackagesToScan("com.jrhot.ticket.domain");
    // Map<String, Object> properties = Maps.newHashMap();
    // properties.put("jaxb.formatted.output", true);
    // properties.put("jaxb.encoding", "UTF-8");
    // properties.put("jaxb.fragment", true);
    // jaxb2Marshaller.setMarshallerProperties(properties);
    // return jaxb2Marshaller;
    // }
    // TODO transaction,cache,shiro,task ...

}
