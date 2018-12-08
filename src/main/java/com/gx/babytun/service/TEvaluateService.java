package com.gx.babytun.service;

import com.gx.babytun.entity.TEvaluate;
import com.gx.babytun.mapper.TEvaluateMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 谷鑫 G x
 * @Classname TEvaluateServiceImpl
 * @Describe:
 * @date 2018/10/19 15:19
 */
@Service
public class TEvaluateService {
    @Resource
    private TEvaluateMapper tEvaluateMapper;

    public List<TEvaluate> findEvaluateByGoodsId(long goodsId) {
        return tEvaluateMapper.findEvaluateByGoodsId(goodsId);
    }
}
