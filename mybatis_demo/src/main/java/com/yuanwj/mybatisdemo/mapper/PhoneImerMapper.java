package com.yuanwj.mybatisdemo.mapper;

import com.yuanwj.mybatisdemo.common.MapperCondition;
import com.yuanwj.mybatisdemo.model.PhoneImei;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/22
 * Description:
 */

public interface PhoneImerMapper {
    PhoneImei findById(Long imeiId);

    List<PhoneImei> findAll();

    List<PhoneImei> findByCondition(MapperCondition condition);
}
