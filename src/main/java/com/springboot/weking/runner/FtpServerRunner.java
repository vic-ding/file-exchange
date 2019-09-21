package com.springboot.weking.runner;

import com.springboot.weking.app.gx.dao.GxNodeChannelDao;
import com.springboot.weking.app.gx.model.GxNodeChannel;
import com.springboot.weking.cmp.ftp.service.FtpManagerService;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FtpServerRunner implements ApplicationRunner, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(FtpServerRunner.class);

    @Override
    public int getOrder() {
        return 300;
    }

    @Autowired
    public DefaultFtpServer ftpServer;

    @Autowired
    public GxNodeChannelDao gxNodeChannelService;

    @Autowired
    public FtpManagerService ftpManagerService;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("【文件服务器】开始运行....");
        List<GxNodeChannel> queryResult = gxNodeChannelService.selectAll(null);
        BaseUser user = new BaseUser();
        for (int i = 0; i < queryResult.size(); i++) {
            String name = queryResult.get(i).getUsername();
            String password = queryResult.get(i).getPassword();
            String homeDirectory = queryResult.get(i).getPath();
            String flag = queryResult.get(i).getFlag();
            user.setName(name);
            user.setPassword(password);
            user.setHomeDirectory(homeDirectory);

            if ("1".equals(flag)) {
                ftpManagerService.add(user);
            }
            logger.info(">> 添加共享用户【" + name + "】，指定目录:" + homeDirectory);
        }
        try {
            ftpServer.start();
            logger.info(">> 数据共享交换服务启动成功！");
        } catch (Exception e) {
            logger.info(">> 数据共享交换服务启动出现异常！！！");
            e.printStackTrace();
        }
    }
}