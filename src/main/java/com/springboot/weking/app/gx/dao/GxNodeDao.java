package com.springboot.weking.app.gx.dao;

import com.springboot.weking.app.gx.model.GxNode;

import java.util.List;


public interface GxNodeDao {

    List<GxNode> selectAll();

    int deleteByPrimaryKey(String nodeCode);

    int insert(GxNode record);

    GxNode selectByPrimaryKey(String nodeCode);

    int updateByPrimaryKeySelective(GxNode record);

    // 条件查询
    List<GxNode> selectAllBySelective(GxNode record);


}