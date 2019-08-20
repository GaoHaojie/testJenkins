package com.test.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author gaohaojie
 * @Date 2019/7/12
 * @Des:
 */
@Configuration
public class ShiroConfig {

    /**
     *创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         *  添加shiro内置过滤器，可以实习那权限相关的拦截器
         *  常用的过滤器
         *      anon:无需认证（登录）可以访问
         *      authc:必须认证才可以访问
         *      perms:该资源必须得到资源授权才可以访问
         *      role:该资源必须得到角色权限才可以访问
         */
        Map<String,String>filterMap = new HashMap<>();
        //设置拦截地址
//        filterMap.put("/add","authc");设置拦截地址
////
        //授权过滤器
        filterMap.put("/add","perms[user:add]");


        //全局过滤
        filterMap.put("/*","authc");
        //放行登录
        filterMap.put("/loginPage","anon");
        filterMap.put("/home","anon");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        //修改跳转的界面
        shiroFilterFactoryBean.setLoginUrl("/loginPage");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     *创建DefaultWebSecurityManager
     * @Qualifier 通过加过@Bean注解的getUserRealm方法获取到UserRealm
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    /**
     * 创建Realm
     * @return
     */
    //将返回实体放到spring容器中
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

}
