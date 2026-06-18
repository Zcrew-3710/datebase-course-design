package com.zufang.renthouse.dto;

import lombok.Data;

/**
 * 管理员查询租户返回DTO（匹配QueryAllTenantInfo存储过程）
 */
@Data
public class AdminTenantDTO {
    private String 租客ID;
    private String 租客姓名;
    private String 联系电话;
    private String 身份证号;
    private Integer 信誉分;
    private String 性别;
    private String 租房冷却期截止日期;
}