package com.zufang.renthouse.entity;

import lombok.Data;
import java.time.LocalDate;

/**
 * 租客表实体（对应Tenant表）
 */
@Data
public class Tenant {
    private String tenantId;          // 租客ID（T001/T002...）
    private String fullName;          // 姓名
    private String contactPhone;      // 联系电话
    private String idCardNumber;      // 身份证号
    private Integer creditScore;      // 信用分（默认80）
    private String gender;            // 性别（男/女）
    private LocalDate rentalCoolingPeriod; // 租房冷却期
}