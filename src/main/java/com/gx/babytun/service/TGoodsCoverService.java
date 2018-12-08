package com.gx.babytun.service;

import com.gx.babytun.entity.TGoodsCover;
import com.gx.babytun.mapper.TGoodsCoverMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 谷鑫 G x
 * @Classname TGoodsCoverServiceImpl
 * @Describe:
 * @date 2018/10/17 16:33
 */
@Service
public class TGoodsCoverService {
    @Resource
    TGoodsCoverMapper tGoodsCoverMapper;

    //如果没有访问数据库，放到缓存，如果有就取缓存中数据不访问数据库
    @Cacheable(value = "covers", key = "#goodsId") //key： covers::1 covers::2
    public List<TGoodsCover> findCoversByGoodsId(long goodsId) {
        return tGoodsCoverMapper.findCoverByGoodsId(goodsId);
    }
}
