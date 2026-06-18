package com.zufang.renthouse.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 合同表实体（对应Contract表）
 */
@Data
public class Contract {
    private Integer contractId;       // 自增主键（1/2/3...）
    private String tenantId;          // 关联租客ID
    private String landlordId;        // 关联房东ID
    // 关联房源联合主键
    private String areaNo;
    private String buildingNo;
    private String roomNo;
//    private BigDecimal rent;          // 月租金（对应存储过程@Rent参数）
//    private BigDecimal deposit;       // 押金（对应存储过程@Deposit参数）
    private LocalDate leaseExpiryDate;// 租期截止日期
    private Integer tenantBreachStatus; // 租客违约状态（0=未违约/1=已违约）
    private Integer landlordBreachStatus; // 房东违约状态（0=未违约/1=已违约）
}
