package com.gx.babytun.mapper;

import com.gx.babytun.entity.TGoodsCover;

import java.util.List;

public interface TGoodsCoverMapper {
    int deleteByPrimaryKey(Integer gcId);

    int insert(TGoodsCover record);

    int insertSelective(TGoodsCover record);

    TGoodsCover selectByPrimaryKey(Integer gcId);

    int updateByPrimaryKeySelective(TGoodsCover record);

    int updateByPrimaryKey(TGoodsCover record);

    List<TGoodsCover> findCoverByGoodsId(long goodsId);
}