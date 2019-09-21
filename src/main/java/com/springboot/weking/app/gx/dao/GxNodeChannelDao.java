package com.springboot.weking.app.gx.dao;

import com.springboot.weking.app.gx.model.GxNodeChannel;

import java.util.List;


public interface GxNodeChannelDao {

    /**
     * 添加数据
     */
    int insert(GxNodeChannel gxNodeChannel);

    /**
     * 按主键查询
     */
    GxNodeChannel selectByPrimaryKey(String id);

    /**
     * 按主键更新数据
     */
    int updateByPrimaryKey(GxNodeChannel gxNodeChannel);

    /**
     * 按主键删除数据-物理删除
     */
    int deleteByPrimaryKey(String id);

    int updateStatus(GxNodeChannel gxNodeChannel);


    List<GxNodeChannel> selectAll(GxNodeChannel gxNodeChannel);

    // 条件查询
    List<GxNodeChannel> selectBySelective(GxNodeChannel gxNodeChannel);

}