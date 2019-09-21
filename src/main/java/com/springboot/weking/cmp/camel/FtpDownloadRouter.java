package com.springboot.weking.cmp.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * 配置相关的路由规则
 */
public class FtpDownloadRouter extends RouteBuilder {

    /**
     * 发送方节点路径，支持以下结构：ftp://127.0.0.1:2121/aaaa?username=admin1&password=123456&delay=5s&move=done&readLock=rename";
     * 首先from语句中填写的“ftp://127.0.0.1:2121/aaaa”表示这个编排好的路由的消息入口：
     * 使用http传输协议，访问本物理节点上任何IP（例如127.0.0.1或者192.168.1.1），
     * 在端口2121上的请求，都可以将HTTP携带的消息传入这个路由。
     * delay：每次读取时间间隔
     * readLock：对正在写入的文件的处理机制
     */
    private String fromNodePath;

    //接收方节点路径，支持以下结构：
    //"file:d:/test/test";
    private String toNodePath;

    private String fromNodeTitle;
    private String toNodeTitle;


    public FtpDownloadRouter(String _fromNodePath, String _toNodePath, String _fromNodeTitle, String _toNodeTitle) {
        this.fromNodePath = _fromNodePath;
        this.toNodePath = _toNodePath;
        this.fromNodeTitle = _fromNodeTitle;
        this.toNodeTitle = _toNodeTitle;
    }

    // from可以理解成消费者：表示从ftp服务上获取数据进行消费
    // to可以理解成生产者：表示将数据发送给file
    //对于消费者(from方法)来说,表示消息从哪里来
    //对于生产者(to方法)来说，表示消息到哪里去
    @Override
    public void configure() {
        from(fromNodePath).to(toNodePath).process(new FtpFileCountProcess(fromNodeTitle, toNodeTitle));
    }
}
