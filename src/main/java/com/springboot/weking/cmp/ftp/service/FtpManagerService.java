package com.springboot.weking.cmp.ftp.service;

import cn.com.goldenwater.core.web.BaseController;
import cn.com.goldenwater.core.web.BaseResponse;
import com.springboot.weking.cmp.ftp.model.GwFtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FtpManagerService extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(FtpManagerService.class);

    @Autowired
    public GwFtpServerFactory serverFactory;

    public BaseResponse add(BaseUser user) {
        //用户名
        try {
            List<Authority> authorities = new ArrayList<>();
            //增加写权限
            authorities.add(new WritePermission());
            user.setAuthorities(authorities);

            //增加该用户
            serverFactory.getUserManager().save(user);

            String[] arr = serverFactory.getUserManager().getAllUserNames();
            for (String anArr : arr) {
                logger.info(">> 共享用户名：" + anArr);
            }
            return buildSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return buildFailResponse();
        }
    }

    public BaseResponse del(String username) {
        try {
            //删除用户
            serverFactory.getUserManager().delete(username);

            String[] arr = serverFactory.getUserManager().getAllUserNames();
            for (String anArr : arr) {
                System.out.println(anArr);
            }
            return buildSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return buildFailResponse();
        }

    }

}
