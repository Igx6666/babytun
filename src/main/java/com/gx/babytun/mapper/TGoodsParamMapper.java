package com.gx.babytun.mapper;

import com.gx.babytun.entity.TGoodsParam;

import java.util.List;

public interface TGoodsParamMapper {
    int deleteByPrimaryKey(Integer gpId);

    int insert(TGoodsParam record);

    int insertSelective(TGoodsParam record);

    TGoodsParam selectByPrimaryKey(Integer gpId);

    int updateByPrimaryKeySelective(TGoodsParam record);

    int updateByPrimaryKey(TGoodsParam record);

    List<TGoodsParam> findParamByGoodsId(long goodsId);
}