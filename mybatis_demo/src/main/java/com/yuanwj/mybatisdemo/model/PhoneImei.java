package com.yuanwj.mybatisdemo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/22
 * Description:
 */
@Data
public class PhoneImei {
    private Long imeiId;

    private Long tenantId;

    private String imeiNum;

    private Long recoredUserId;

    private Date recordDate;

}
