package com.gx.babytun.mapper;

import com.gx.babytun.entity.TCategory;

public interface TCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(TCategory record);

    int insertSelective(TCategory record);

    TCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(TCategory record);

    int updateByPrimaryKey(TCategory record);
}