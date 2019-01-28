package com.yuanwj.mybatisdemo.archive.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/28
 * Description:
 */
@Data
public class ArchiveOrderBox {
    private Long id;

    private Long ownerId;

    private Long storeId;

    private Long orderId;

    private Long boxId;

    private String boxCode;

    private BigDecimal boxAmount;

    private String boxName;

    private String expCode;

    private Integer boxNum;

    private Date rowver;
}
