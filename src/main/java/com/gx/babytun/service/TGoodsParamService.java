package com.gx.babytun.service;

import com.gx.babytun.entity.TGoodsParam;
import com.gx.babytun.mapper.TGoodsParamMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 谷鑫 G x
 * @Classname TGoodsParamServiceImpl
 * @Describe:
 * @date 2018/10/17 16:52
 */
@Service
public class TGoodsParamService {
    @Resource
    TGoodsParamMapper tGoodsParamMapper;

    //如果没有访问数据库，放到缓存，如果有就取缓存中数据不访问数据库
    @Cacheable(value = "params", key = "#goodsId") //key： params::1 params::2
    public List<TGoodsParam> findParamByGoodsId(long goodsId) {
        return tGoodsParamMapper.findParamByGoodsId(goodsId);
    }

}
