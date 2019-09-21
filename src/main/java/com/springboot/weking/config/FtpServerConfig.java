package com.springboot.weking.config;

import com.springboot.weking.cmp.ftp.model.GwFtpServerFactory;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
class FtpServerConfig {

    @Value("${gx.local.node.port}")
    private int serverPort;

    @Bean
    @Order(100)
    public GwFtpServerFactory gwFtpServerFactory() {

        GwFtpServerFactory serverFactory = new GwFtpServerFactory();

        ListenerFactory factory = new ListenerFactory();
        //设置监听端口

        factory.setPort(serverPort);

        //替换默认监听
        serverFactory.addListener("default", factory.createListener());

        return serverFactory;
    }


    @Bean
    @Order(110)
    public DefaultFtpServer defaultFtpServer(GwFtpServerFactory serverFactory) {
        return new DefaultFtpServer(serverFactory.serverContext);
    }

}
