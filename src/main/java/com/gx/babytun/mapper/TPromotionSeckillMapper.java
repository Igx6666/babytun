package com.gx.babytun.mapper;

import com.gx.babytun.entity.TPromotionSeckill;

import java.util.List;

public interface TPromotionSeckillMapper {
    int deleteByPrimaryKey(Long psId);

    int insert(TPromotionSeckill record);

    int insertSelective(TPromotionSeckill record);

    TPromotionSeckill selectByPrimaryKey(Long psId);

    int updateByPrimaryKeySelective(TPromotionSeckill record);

    int updateByPrimaryKey(TPromotionSeckill record);

    List<TPromotionSeckill> findPromotionByStatus0();

    TPromotionSeckill findById(long id);

    List<TPromotionSeckill> findByEndTime();
}