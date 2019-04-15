package com.lmk.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @ClassName AuthorizationServerConfigurer
 * @Description TODO Oauth2的授权服务器配置
 * @Author liumingkang
 * @Date 2019-04-13 16:06
 * @Version 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("myAuthenticationManager")
    private AuthenticationManager myAuthenticationManager;


    public AuthorizationServerConfigurer() {
        super();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置客户端具体信息
        //来源1，内存 2，jdbc
        clients.inMemory().withClient("client")
                .secret("123456").scopes("read").authorizedGrantTypes("authorization_code")
                //给客户端返回认证成功的回调url
                .redirectUris("http://localhost:8881/oauth2/callback");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // todo
        endpoints.authenticationManager(myAuthenticationManager);
    }
}
