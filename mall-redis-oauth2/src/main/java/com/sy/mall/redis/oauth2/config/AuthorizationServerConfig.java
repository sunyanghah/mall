package com.sy.mall.redis.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created by dell on 2018/11/9.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    CustomUserService customUserService(){
        return new CustomUserService();
    }

    @Bean
    CustomClientService customClientService(){
        return new CustomClientService();
    }

    /**
     * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientService());
//        //使用内存存储
//        clients.inMemory()
//                //分配客户端账号
//                .withClient("client")
//                .secret("client-secret")
//                //支持的授权类型
//                .authorizedGrantTypes("refresh_token", "password")
//                .scopes("server")
//                //token有效时长
//                .accessTokenValiditySeconds(1200)
//                //refreshToken有效时长
//                .refreshTokenValiditySeconds(50000);
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        //设置签名密钥
//        jwtAccessTokenConverter.setSigningKey("1qaz@wsx?Z");
//        return jwtAccessTokenConverter;
//    }

    //使用
    @Bean
    public TokenStore redisTokenStore() {
        return new MyRedisTokenStore(redisConnectionFactory);
    }

    /**
     * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
//                .accessTokenConverter(jwtAccessTokenConverter())
                .reuseRefreshTokens(true)
                //配置以生效password模式
                .authenticationManager(authenticationManager)
                .userDetailsService(customUserService());
    }

    /**
     * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }
}
