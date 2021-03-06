package com.yuanwj.mybatisdemo.service.impl;

import com.yuanwj.mybatisdemo.common.Criteria;
import com.yuanwj.mybatisdemo.common.MapperCondition;
import com.yuanwj.mybatisdemo.mapper.PhoneImerMapper;
import com.yuanwj.mybatisdemo.model.PhoneImei;
import com.yuanwj.mybatisdemo.service.PhoneImeiService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/25
 * Description:
 */
@Service
@Transactional
public class PhoneImeiServiceImpl implements PhoneImeiService {

    @Resource
    private PhoneImerMapper phoneImerMapper;

    @Resource
    private RedisTemplate<String, Object> template;

    @Override
    public List<PhoneImei> findAll() {
        List<PhoneImei> phoneImeis = phoneImerMapper.findAll();
        return phoneImeis;
    }

    @Override
    public PhoneImei findById(Long id) {
        MapperCondition mapperCondition = new MapperCondition();
        Criteria criteria = mapperCondition.createCriteria(PhoneImei.class);
        criteria.andEuqal("imeiId", id);
        criteria.andEuqal("tenantId", 50001);
        List<PhoneImei> phoneImeis = phoneImerMapper.findByCondition(mapperCondition);
        return phoneImeis.get(0);
    }
}
