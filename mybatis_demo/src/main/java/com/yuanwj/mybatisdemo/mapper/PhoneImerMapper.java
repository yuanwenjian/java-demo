package com.yuanwj.mybatisdemo.mapper;

import com.yuanwj.mybatisdemo.model.PhoneImei;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/22
 * Description:
 */

public interface PhoneImerMapper {
    PhoneImei findById(Long imeiId);
}
