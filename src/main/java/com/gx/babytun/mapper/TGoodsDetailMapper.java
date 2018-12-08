package com.gx.babytun.mapper;

import com.gx.babytun.entity.TGoodsDetail;

import java.util.List;

public interface TGoodsDetailMapper {
    int deleteByPrimaryKey(Integer gdId);

    int insert(TGoodsDetail record);

    int insertSelective(TGoodsDetail record);

    TGoodsDetail selectByPrimaryKey(Integer gdId);

    int updateByPrimaryKeySelective(TGoodsDetail record);

    int updateByPrimaryKey(TGoodsDetail record);

    List<TGoodsDetail> findDetailByGoodsId(long goodsId);
}