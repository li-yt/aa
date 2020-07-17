package com.fh.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fh.shiro.componect.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.redisPassword}")
    private String redisPassword;

    //不需要在此处配置权限页面,因为上面的ShiroFilterFactoryBean已经配置过,但是此处必须存在,因为shiro-spring-boot-web-starter或查找此Bean,没有会报错
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        return new DefaultShiroFilterChainDefinition();
    }
    /**
     * shiro的过滤器配置
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        log.info("=============过滤器加载开始===============");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        //注入setSecurityManager需要进行认证的请求完成认证。
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 设置默认的登录页面
        shiroFilterFactoryBean.setLoginUrl("/");
        // 用户访问未对其授权时所访问的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/notLogin");
        //配置请求的过滤规则
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login/**","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/**","authc,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("=============过滤器加载结束===============");
        return shiroFilterFactoryBean;

    }
    /**
     * 这个是shiro的核心控制器
     *
     * @return
     */
    /*
     * <bean id="securityManager" class="import org.apache.shiro.web.mgt.DefaultWebSecurityManager">
     *     <property ref="shiroRealm"/>
     *     <property ref="sessionManager"/>
     *     <property ref="acheManager"/>
     *  </bean>
     * */
    @Bean
    public SecurityManager securityManager(){
        log.info("=============shiro的securityManager安全管理器加载开始===============");
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //注入自定的shiroRealm进行登录认证和权限授权
        securityManager.setRealm(shiroRealm());
        //注入sessionManager管理器
        securityManager.setSessionManager(sessionManager());
        //注入cacheManager管理器
        securityManager.setCacheManager(redisCacheManager());
        log.info("=============shiro的securityManager安全管理器加载结束===============");
        return securityManager;
    }


    /**
     * 自定义用户登录和授权方法
     * @return
     */
    @Bean
    public Realm shiroRealm(){
        UserRealm shireRealm= new UserRealm();
        //加入缓存中
        shireRealm.setCacheManager(redisCacheManager());
        shireRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shireRealm;
    }

    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
        return kickoutSessionControlFilter;
    }
    /**
     * redis的缓存管理器
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setExpire(3600);
        redisCacheManager.setKeyPrefix("shopAdmin:cache");
        //跟主键ID保持一致
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * redis的配置
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host + ":" + port);
        if(StringUtils.isNotBlank(redisPassword)){
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

    /**
     *
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("shopAdmin:session");
        redisSessionDAO.setExpire(3600);
        return  redisSessionDAO;
    }

    /**
     * sessionManager管理器的配置
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        return defaultWebSessionManager;

    }



    /**
     * shiro声明周期处理器
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        log.info("========shiro生命周期处理器设置=========");
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro的注解（如@RequiresRoles，@RequiresPermissions）
     * 需借助springAOP扫描使用shiro注解类，并在必要时进行安全逻辑认证
     * 需要配置两个bean(DefaultAdvisorAutoProxyCreator)(可选)
     * 和(AuthorizationAttributeSourceAdvisor)即可实现该功能
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator autoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }


    /**
     * 开启注解权限
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        log.info("===============开启shiro后台注释================");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;

    }

    /**
     * 开启前台标签
     * */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}