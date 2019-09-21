package com.springboot.weking.app.gx.dao;

import com.springboot.weking.app.gx.model.GxFileDir;

import java.util.List;


public interface GxFileDirDao {

    int deleteByPrimaryKey(String id);

    int insert(GxFileDir record);

    GxFileDir selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(GxFileDir record);

    List<GxFileDir> selectAll();

    List<GxFileDir> selectBySelective(GxFileDir record);

    // 校验目录地址的唯一性
    GxFileDir selectByPath(String path);
}