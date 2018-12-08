package com.gx.babytun.mapper;

import com.gx.babytun.entity.TGoods;

import java.util.List;

public interface TGoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(TGoods record);

    int insertSelective(TGoods record);

    TGoods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(TGoods record);

    int updateByPrimaryKey(TGoods record);

    TGoods findById(long goodsId);

    List<TGoods> findGoodsAll();

    List<TGoods> findLastTime5();
}