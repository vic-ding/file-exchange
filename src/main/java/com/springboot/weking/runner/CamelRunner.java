package com.springboot.weking.runner;

import com.springboot.weking.app.gx.dao.GxFileDirDao;
import com.springboot.weking.app.gx.dao.GxNodeDao;
import com.springboot.weking.app.gx.model.GxFileDir;
import com.springboot.weking.app.gx.model.GxNode;
import com.springboot.weking.utils.ObjectUtils;
import com.springboot.weking.cmp.camel.CamelActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CamelRunner implements ApplicationRunner, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(CamelRunner.class);

    @Override
    public int getOrder() {
        return 200;
    }


    @Autowired
    private GxFileDirDao gxFileDirMapper;

    @Autowired
    private CamelActionService camelActionService;

    @Autowired
    private GxNodeDao gxNodeMapper;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("【文件传输组件】开始运行....");

        // 第一步：通过读取配置目录，添加运行启动文件交换任务
        List<GxFileDir> list = gxFileDirMapper.selectAll();
        for (int i = 0; i < list.size(); i++) {
            GxFileDir fileDir = list.get(i);
            String path = fileDir.getPath();
            String username = fileDir.getUsername();
            String password = fileDir.getPassword();
            String nodeCode = fileDir.getNodeCode();
            GxNode gxNode = gxNodeMapper.selectByPrimaryKey(nodeCode);

            if (ObjectUtils.isNotNull(gxNode)) {
                String nodeIp = gxNode.getGxNodeIP();
                String nodePort = gxNode.getGxNodePort();
                String toNode = gxNode.getNodeCode();
                camelActionService.upload(path, username, password, nodeIp, nodePort, toNode);

                logger.info(">> 文件上传服务：IP：" + nodeIp + " ，PORT：" + nodePort + "，用户【" + username + "】，本地路径：" + path);
            }
        }
    }
}