package com.gx.babytun.service;

import com.gx.babytun.entity.TGoodsDetail;
import com.gx.babytun.mapper.TGoodsDetailMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 谷鑫 G x
 * @Classname TGoodsDetailServiceImpl
 * @Describe:
 * @date 2018/10/17 16:45
 */
@Service
public class TGoodsDetailService  {
    @Resource
    TGoodsDetailMapper tGoodsDetailMapper;

    //如果没有访问数据库，放到缓存，如果有就取缓存中数据不访问数据库
    @Cacheable(value = "details", key = "#goodsId") //key： details::1 details::2
    public List<TGoodsDetail> findDetailByGoodsId(long goodsId) {
        return tGoodsDetailMapper.findDetailByGoodsId(goodsId);
    }
}
