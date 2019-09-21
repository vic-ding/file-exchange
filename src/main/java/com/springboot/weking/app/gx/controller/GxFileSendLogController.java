package com.springboot.weking.app.gx.controller;


import cn.com.goldenwater.core.web.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.weking.app.gx.dao.GxFileSendLogDao;
import com.springboot.weking.app.gx.model.GxFileSendLog;
import com.springboot.weking.core.pages.Page;
import com.springboot.weking.core.pages.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("gx/file/send/log")
public class GxFileSendLogController extends BaseController {

    @Autowired
    private GxFileSendLogDao gxFileSendLogMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page list(int page,int limit) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy("SND_TIME desc");
        List<GxFileSendLog> list = gxFileSendLogMapper.selectAll();
        // 取分页信息
        PageInfo<GxFileSendLog> pageInfo = new PageInfo<>(list);
        return PageUtils.convert(pageInfo);
    }


}