package com.springboot.weking.app.gx.dao;

import com.springboot.weking.app.gx.model.GxFileSendLog;

import java.util.List;

public interface GxFileSendLogDao {

    int deleteByPrimaryKey(String sndLsh);

    int insert(GxFileSendLog record);

    int insertSelective(GxFileSendLog record);

    GxFileSendLog selectByPrimaryKey(String sndLsh);

    int updateByPrimaryKeySelective(GxFileSendLog record);

    int updateByPrimaryKey(GxFileSendLog record);

    List<GxFileSendLog> selectAll();
}