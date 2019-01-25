package com.yuanwj.mybatisdemo.service;

import com.yuanwj.mybatisdemo.model.PhoneImei;

import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/25
 * Description:
 */
public interface PhoneImeiService {
    List<PhoneImei> findAll();

    PhoneImei findById(Long id);
}
