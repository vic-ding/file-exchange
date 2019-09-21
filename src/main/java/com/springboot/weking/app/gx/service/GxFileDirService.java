package com.springboot.weking.app.gx.service;

import com.springboot.weking.app.gx.dao.GxNodeDao;
import com.springboot.weking.app.gx.model.GxFileDir;
import com.springboot.weking.app.gx.model.GxNode;
import com.springboot.weking.cmp.camel.CamelActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GxFileDirService {


    @Autowired
    GxNodeDao gxNodeMapper;

    @Autowired
    CamelActionService camelActionService;

    public void basicInfo(GxFileDir gxFileDir) {
        String password = gxFileDir.getPassword();
        String path = gxFileDir.getPath();
        String username = gxFileDir.getUsername();
        String nodeCode = gxFileDir.getNodeCode();

        GxNode gxNode = gxNodeMapper.selectByPrimaryKey(nodeCode);
        String nodePort = gxNode.getGxNodePort();
        String nodeIp = gxNode.getGxNodeIP();
        String toNode = gxNode.getNodeCode();

        camelActionService.remove(path);
        camelActionService.upload(path, username, password, nodeIp, nodePort, toNode);

    }

}