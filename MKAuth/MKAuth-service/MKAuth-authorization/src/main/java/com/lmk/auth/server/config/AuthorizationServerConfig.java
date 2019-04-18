package com.lmk.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @ClassName AuthorizationServerConfig
 * @Description TODO
 * @Author liumingkang
 * @Date 2019-04-18 22:37
 * @Version 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //必须要开启 这样客户端才能正常post或得accessToken
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // todo 后期设计jdbc表
        clients
                .inMemory().withClient("mkhome")
                .secret("{bcrypt}"+passwordEncoder().encode("minisec"))
                .scopes("all")
                .redirectUris("http://localhost:8881/auth/getAccessToken")
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
                        "password", "implicit")
                .accessTokenValiditySeconds(120)
                .refreshTokenValiditySeconds(60);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);
    }

    // todo 改为 自定义实现
    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager im = new InMemoryUserDetailsManager();
        im.createUser(User.withUsername("lmk1010")
                .password("{bcrypt}"+passwordEncoder().encode("1010"))
                .authorities("USER").build());
        return im;
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
