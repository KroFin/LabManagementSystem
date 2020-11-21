package com.iotdreamclub.demo.config;

import com.iotdreamclub.demo.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(false);
        return userRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        //创建web项目的安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());  //配置Realm数据
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean (SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("login");
        factoryBean.setUnauthorizedUrl("404.html");
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/login.html","anon");   //anon 不拦截，authc 拦截
        map.put("/register.html","anon");
        map.put("/failed.html","anon");
        map.put("/login","anon");
        map.put("/register","anon");
        map.put("/api/getDeviceInfoAPI","anon");
        map.put("api/getDeviceInfoAPI","anon");
        map.put("userNameExisted","anon");
        map.put("/userNameExisted","anon");
        map.put("sendVerifyNumber","anon");
        map.put("/sendVerifyNumber","anon");
        map.put("checkCode","anon");
        map.put("/checkCode","anon");
        map.put("login","anon");
        map.put("register","anon");
        map.put("/static/**","anon");
        map.put("logout","anon");

        map.put("/**","authc");         //拦截其余的所有资源
        factoryBean.setFilterChainDefinitionMap(map); //配置拦截的规则
        return factoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
