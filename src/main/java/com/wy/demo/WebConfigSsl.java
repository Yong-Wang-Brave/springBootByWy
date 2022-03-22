/*
package com.wy.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfigSsl {
    */
/**
     * http 转 https
     *//*

    @Bean
    public Connector connector() {
        Connector connector = null;
        try {
            connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setScheme("http");
            // 监听的http端口
            connector.setPort(8082);
            connector.setSecure(false);
            // 监听到http端口后跳转的https端口
            connector.setRedirectPort(8081);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connector;
    }

    */
/**
     * 拦截所有的请求
     *//*

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

}
*/
