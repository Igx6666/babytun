package com.gx.babytun.service;

import com.gx.babytun.entity.TGoods;
import com.gx.babytun.mapper.TGoodsMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 谷鑫 G x
 * @Classname TGoodsServiceImpl
 * @Describe:
 * @date 2018/10/17 15:59
 */
@Service
public class TGoodsService{

    @Resource
    TGoodsMapper tGoodsMapper;

    //如果没有访问数据库，放到缓存，如果有就取缓存中数据不访问数据库
    @Cacheable(value = "goods", key = "#goodsId") //key： goods::1 goods::2
    public TGoods getGoods(Long goodsId) {
        return tGoodsMapper.findById(goodsId);
    }

    public List<TGoods> getGoods() {
        return tGoodsMapper.findGoodsAll();
    }

    public List<TGoods> findLastTime5() {
        return tGoodsMapper.findLastTime5();
    }
}
