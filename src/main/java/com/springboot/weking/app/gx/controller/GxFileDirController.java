package com.springboot.weking.app.gx.controller;

import cn.com.goldenwater.core.web.BaseController;
import cn.com.goldenwater.core.web.BaseResponse;
import cn.com.goldenwater.id.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.weking.app.gx.dao.GxFileDirDao;
import com.springboot.weking.app.gx.model.GxFileDir;
import com.springboot.weking.app.gx.service.GxFileDirService;
import com.springboot.weking.core.pages.Page;
import com.springboot.weking.core.pages.PageUtils;
import com.springboot.weking.cmp.camel.CamelActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("gx/file/dir")
public class GxFileDirController extends BaseController {

    @Autowired
    private GxFileDirDao gxFileDirMapper;

    @Autowired
    private CamelActionService camelActionService;

    @Autowired
    private GxFileDirService gxFileDirService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page list(int page, int limit) {
        // 放到查询数据之前
        PageHelper.startPage(page, limit);
        // 排序
        PageHelper.orderBy("TM desc");

        List<GxFileDir> list = gxFileDirMapper.selectAll();

        PageInfo<GxFileDir> pageInfo = new PageInfo<>(list);

        return PageUtils.convert(pageInfo);
    }


    /**
     * 新增数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse insert(GxFileDir gxFileDir) {
        String code = UuidUtil.uuid();
        gxFileDir.setId(code);
        gxFileDir.setTm(new Date());
        int ret = gxFileDirMapper.insert(gxFileDir);

        if (ret > 0) {
            gxFileDirService.basicInfo(gxFileDir);
            return buildSuccessResponse();
        }
        return buildFailResponse();
    }

    /**
     * 条件查询
     */
    @RequestMapping(value = "/list/selective", method = RequestMethod.GET)
    public Page listSelective(int page, int limit, GxFileDir gxFileDir) {
        // 放到查询数据之前
        PageHelper.startPage(page, limit);
        // 排序
        PageHelper.orderBy("TM desc");

        List<GxFileDir> list = gxFileDirMapper.selectBySelective(gxFileDir);

        PageInfo<GxFileDir> pageInfo = new PageInfo<>(list);

        return PageUtils.convert(pageInfo);
    }

    /**
     * 删除数据
     * <p>
     * 第一步：删除目录数据；
     * <p>
     * 第二步：删除通道数据；
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse delete(String id, String path) {
        camelActionService.remove(path);
        gxFileDirMapper.deleteByPrimaryKey(id);
        return buildSuccessResponse();
    }

    /**
     * 校验目录地址的唯一性
     */
    @RequestMapping(value = "/check/path", method = RequestMethod.POST)
    public Boolean checkName(String path) {
        GxFileDir gxFileDir = gxFileDirMapper.selectByPath(path);
        if (gxFileDir != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取选中数据的信息并跳转到相应的页面
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(String id) {
        ModelAndView mav = new ModelAndView();
        GxFileDir gxFileDir = gxFileDirMapper.selectByPrimaryKey(id);
        if (gxFileDir != null) {
            mav.addObject("gxFileDir", gxFileDir);
            mav.setViewName("gx_file_dir_edit.html");
        }
        return mav;
    }

    /**
     * 修改数据
     * <p>
     * 第一步：修改目录数据；
     * <p>
     * 第二步：删除通道数据；
     * <p>
     * 第三步：增加通道数据；
     */
    @RequestMapping(value = "/update")
    public BaseResponse update(GxFileDir gxFileDir) {
        int ret = gxFileDirMapper.updateByPrimaryKeySelective(gxFileDir);

        if (ret > 0) {
            gxFileDirService.basicInfo(gxFileDir);
            return buildSuccessResponse();
        }
        return buildFailResponse();
    }


}