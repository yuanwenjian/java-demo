package com.yuanwj.mybatisdemo.service.impl;

import com.yuanwj.mybatisdemo.mapper.PhoneImerMapper;
import com.yuanwj.mybatisdemo.model.PhoneImei;
import com.yuanwj.mybatisdemo.service.PhoneImeiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    public List<PhoneImei> findAll() {
        List<PhoneImei> phoneImeis = phoneImerMapper.findAll();
        return phoneImeis;
    }

    @Override
    public PhoneImei findById(Long id) {
        PhoneImei phoneImei = phoneImerMapper.findById(id);
        return phoneImei;
    }
}
