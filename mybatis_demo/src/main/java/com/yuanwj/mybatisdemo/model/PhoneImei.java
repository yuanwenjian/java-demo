package com.yuanwj.mybatisdemo.model;

import com.yuanwj.mybatisdemo.common.Column;
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
    @Column(value = "imei_id")
    private Long imeiId;

    @Column(value = "tenant_id")
    private Long tenantId;

    @Column(value = "imei_num")
    private String imeiNum;

    @Column(value = "record_user_id")
    private Long recoredUserId;

    @Column(value = "record_date")
    private Date recordDate;

}
