package com.lmk.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author liumingkang
 * @Date 2019-04-13 15:08
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置授权存储对象信息 目前测试使用内存存储 后期改为JDBC数据库读取存储
        //类似Shiro的Realm数据源配置
        auth.inMemoryAuthentication().withUser("lmk1010")
                .password("1010")
                .roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //默认不做修改
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //主要配置http的一些配置
        http.httpBasic()
                //配置csrf攻击防范
                .and().csrf().disable()
                //配置url匹配进行进行认证
                .authorizeRequests().antMatchers("/login").permitAll()
                //
                .anyRequest().authenticated()
                //开启表单认证
                .and().formLogin()
                //开启注销的许可认证
                .and().logout().permitAll();
    }

    @Override
    @Bean(name = "myAuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
