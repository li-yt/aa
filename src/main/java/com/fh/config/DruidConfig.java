package com.fh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getDataSource(){
        return new DruidDataSource();
    }
    //配置Druid的监控   http://localhost:8080/druid 进入自己的druid管理后台
    //1.配置管理后台的servlet
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "");//默认全部访问
        //黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.15.21");//自己设置
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
    //2.配置监控的filter
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif," +
                "*.jpg,*.png, *.css,*.ico,/druid/*"+"*.ico");
        return filterRegistrationBean;
    }
}
