package com.springboot.weking.app.gx.controller;

import cn.com.goldenwater.core.web.BaseController;
import cn.com.goldenwater.core.web.BaseResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.weking.app.gx.dao.GxNodeDao;
import com.springboot.weking.app.gx.model.GxNode;
import com.springboot.weking.core.pages.Page;
import com.springboot.weking.core.pages.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("gx/node/")
public class GxNodeController extends BaseController {

    @Autowired
    private GxNodeDao gxNodeMapper;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page list(int page, int limit) {
        // 放到查询数据之前
        PageHelper.startPage(page, limit);

        List<GxNode> list = gxNodeMapper.selectAll();

        PageInfo<GxNode> pageInfo = new PageInfo<>(list);

        return PageUtils.convert(pageInfo);
    }


    @RequestMapping(value = "get/all", method = RequestMethod.GET)
    public List<GxNode> listAll() {
        return gxNodeMapper.selectAll();
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public BaseResponse insert(GxNode gxNode) {
        gxNode.setTm(new Date());
        int ret = gxNodeMapper.insert(gxNode);
        if (ret == 0) {
            return buildFailResponse();
        }
        return buildSuccessResponse();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse delete(String nodeCode) {
        int ret = gxNodeMapper.deleteByPrimaryKey(nodeCode);
        if (ret == 0) {
            return buildFailResponse();
        }
        return buildSuccessResponse();
    }

    /**
     * 条件查询
     */
    @RequestMapping(value = "/list/selective", method = RequestMethod.GET)
    public Page listSelective(int page, int limit, GxNode gxNode) {
        // 放到查询数据之前
        PageHelper.startPage(page, limit);
        // 排序
        PageHelper.orderBy("TM desc");

        List<GxNode> list = gxNodeMapper.selectAllBySelective(gxNode);

        PageInfo<GxNode> pageInfo = new PageInfo<>(list);

        return PageUtils.convert(pageInfo);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(String nodeCode) {
        ModelAndView mav = new ModelAndView();
        GxNode gxNode = gxNodeMapper.selectByPrimaryKey(nodeCode);
        if (gxNode != null) {
            mav.addObject("gxNode", gxNode);
            mav.setViewName("gx_node_edit.html");
        }
        return mav;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse update(GxNode gxNode) {
        int ret = gxNodeMapper.updateByPrimaryKeySelective(gxNode);
        if (ret == 0) {
            return buildFailResponse();
        }
        return buildSuccessResponse();
    }


    @RequestMapping(value = "/check/nodeCode", method = RequestMethod.POST)
    public Boolean checkName(String nodeCode) {
        GxNode gxNode = gxNodeMapper.selectByPrimaryKey(nodeCode);
        if (gxNode != null) {
            return false;
        } else {
            return true;
        }
    }


}