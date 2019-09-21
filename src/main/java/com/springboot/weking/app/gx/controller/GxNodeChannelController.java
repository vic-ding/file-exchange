package com.springboot.weking.app.gx.controller;

import cn.com.goldenwater.core.web.BaseController;
import cn.com.goldenwater.core.web.BaseResponse;
import cn.com.goldenwater.id.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.weking.app.gx.dao.GxNodeChannelDao;
import com.springboot.weking.app.gx.model.GxNodeChannel;
import com.springboot.weking.cmp.ftp.service.FtpManagerService;
import com.springboot.weking.core.pages.Page;
import com.springboot.weking.core.pages.PageUtils;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("gx/node/channel")
public class GxNodeChannelController extends BaseController {

    @Autowired
    private GxNodeChannelDao gxNodeChannelDao;

    @Autowired
    private FtpManagerService ftpManagerService;

    /**
     * 列表查询并按时间倒序排序
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page list(int page, int limit) {
        PageHelper.startPage(page, limit);

        PageHelper.orderBy("TM desc");

        List<GxNodeChannel> queryResult = gxNodeChannelDao.selectAll(null);

        PageInfo<GxNodeChannel> pageInfo = new PageInfo<>(queryResult);

        return PageUtils.convert(pageInfo);
    }

    /**
     * 新增数据
     * <p>
     * 第一步：新增通道数据；
     * <p>
     * 第二步：新增用户数据；
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse insert(GxNodeChannel gxNodeChannel, BaseUser user) {
        String name = gxNodeChannel.getUsername();
        String password = gxNodeChannel.getPassword();
        String homeDirectory = gxNodeChannel.getPath();
        String flag = gxNodeChannel.getFlag();

        String uuid = UuidUtil.uuid();
        gxNodeChannel.setId(uuid);
        gxNodeChannel.setPassword(password);
        gxNodeChannel.setTm(new Date());
        int ret = gxNodeChannelDao.insert(gxNodeChannel);

        if (ret > 0) {
            user.setName(name);
            user.setPassword(password);
            user.setHomeDirectory(homeDirectory);

            if ("1".equals(flag)) {
                ftpManagerService.add(user);
            }
        }
        return buildSuccessResponse();
    }

    /**
     * 条件查询并按时间倒序排序
     */
    @RequestMapping(value = "/list/selective", method = RequestMethod.GET)
    public Page listSelective(int page, int limit, GxNodeChannel gxNodeChannel) {
        // 放到查询数据之前
        PageHelper.startPage(page, limit);
        // 排序
        PageHelper.orderBy("TM desc");

        List<GxNodeChannel> list = gxNodeChannelDao.selectBySelective(gxNodeChannel);

        PageInfo<GxNodeChannel> pageInfo = new PageInfo<>(list);

        return PageUtils.convert(pageInfo);
    }

    /**
     * 查看选中的数据并进入到相应页面
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(String id) {
        ModelAndView mav = new ModelAndView();
        GxNodeChannel gxNodeChannel = gxNodeChannelDao.selectByPrimaryKey(id);
        if (gxNodeChannel != null) {
            mav.addObject("gxNodeChannel", gxNodeChannel);
        }
        mav.setViewName("gx_node_channel_edit.html");
        return mav;
    }

    /**
     * 修改数据
     * <p>
     * 第一步：修改通道数据；
     * <p>
     * 第二步：根据不同的状态对用户权限执行不同的操作；
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse update(GxNodeChannel gxNodeChannel, BaseUser user) {
        String name = gxNodeChannel.getUsername();
        String password = gxNodeChannel.getPassword();
        String homeDirectory = gxNodeChannel.getPath();
        String flag = gxNodeChannel.getFlag();

        int ret = gxNodeChannelDao.updateByPrimaryKey(gxNodeChannel);
        if (ret > 0) {
            user.setName(name);
            user.setPassword(password);
            user.setHomeDirectory(homeDirectory);

            if ("1".equals(flag)) {
                GxNodeChannel nodeChannel = gxNodeChannelDao.selectByPrimaryKey(gxNodeChannel.getId());
                if (nodeChannel != null) {
                    ftpManagerService.del(nodeChannel.getUsername());
                }
                ftpManagerService.add(user);
            } else if ("0".equals(flag)) {
                ftpManagerService.del(name);
            }
        }
        return buildSuccessResponse();
    }

    /**
     * 删除数据
     * <p>
     * 第一步：删除通道数据；
     * <p>
     * 第二步：删除用户数据；
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse delete(String id) {
        //第一步：查询是否有此用户，有的情况就是删除。
        GxNodeChannel nodeChannel = gxNodeChannelDao.selectByPrimaryKey(id);
        if (nodeChannel != null) {
            ftpManagerService.del(nodeChannel.getUsername());
        }

        //第二步：删除通道记录
        gxNodeChannelDao.deleteByPrimaryKey(id);
        return buildSuccessResponse();
    }


}