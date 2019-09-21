package com.springboot.weking.cmp.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 郑重其事的写下 helloworld for Apache Camel
 * 路由规则配置器
 */
@Service
public class CamelActionService {

    @Autowired
    CamelContext camelContext; // 这是camel上下文对象，整个路由的驱动全靠它了。

    @Value("${gx.local.node.name}")
    private String nodeName;

    /**
     * 传输文件
     */
    public void upload(String path, String username, String password, String nodeIp, String nodePort, String toNode) {
        String fromDir = "file:" + path;
        String toDir = "ftp://" + username + ":" + password + "@" + nodeIp + ":" + nodePort + "/?ftpClient.controlEncoding=UTF-8";
        System.out.println(toDir);
        String fromNode = nodeName;

        try {
            // 将我们编排的一个完整消息路由过程，加入到上下文中
            camelContext.addRoutes(new FtpDownloadRouter(fromDir, toDir, fromNode, toNode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除
     */
    public void remove(String path) {
        try {
            List<Route> list = camelContext.getRoutes();
            for (int i = 0; i < list.size(); i++) {
                Route r = list.get(i);
                Consumer c = r.getConsumer();
                String endpoint = String.valueOf(c.getEndpoint());
                if (endpoint.equals("file://" + path)) {
                    String routeId = r.getId();
                    camelContext.stopRoute(routeId);
                    camelContext.removeRoute(routeId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
