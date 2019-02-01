package com.yuanwj.mybatisdemo.service.impl;

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
        PhoneImei phoneImei = phoneImerMapper.findById(id);
        template.opsForList().leftPush(phoneImei.getImeiId() + "", phoneImei);
        PhoneImei imei = (PhoneImei) template.opsForList().leftPop(phoneImei.getImeiId() + "");
        System.out.println(imei.getImeiId());
        return phoneImei;
    }
}
