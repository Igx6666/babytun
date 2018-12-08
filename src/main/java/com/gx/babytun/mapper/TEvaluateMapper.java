package com.gx.babytun.mapper;

import com.gx.babytun.entity.TEvaluate;

import java.util.List;

public interface TEvaluateMapper {
    int deleteByPrimaryKey(Long evaluateId);

    int insert(TEvaluate record);

    int insertSelective(TEvaluate record);

    TEvaluate selectByPrimaryKey(Long evaluateId);

    int updateByPrimaryKeySelective(TEvaluate record);

    int updateByPrimaryKey(TEvaluate record);

    List<TEvaluate> findEvaluateByGoodsId(long goodsId);
}