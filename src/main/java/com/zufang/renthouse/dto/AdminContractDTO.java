package com.zufang.renthouse.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * 管理员查询合同返回DTO（匹配QueryAllContractInfo存储过程）
 */
@Data
public class AdminContractDTO {
    private Integer 合同编号;
    private String 租客ID;
    private String 房东ID;
    private String 房源编号;
    private String 区域号;
    private String 楼栋号;
    private String 房间号;
    private LocalDate 租期截止日期;
    private Integer 剩余到期天数;
    private String 租客违约状态;
    private String 房东违约状态;
}