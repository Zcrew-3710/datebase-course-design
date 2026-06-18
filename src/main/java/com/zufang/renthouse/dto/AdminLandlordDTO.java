package com.zufang.renthouse.dto;

import lombok.Data;

/**
 * 管理员查询房东返回DTO（匹配QueryAllLandlordInfo存储过程）
 */
@Data
public class AdminLandlordDTO {
    private String 房东ID;
    private String 房东姓名;
    private String 身份证号;
    private Integer 信誉分;
    private String 管理楼栋号;
    private String 联系电话;
}