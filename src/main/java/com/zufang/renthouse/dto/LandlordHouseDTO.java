package com.zufang.renthouse.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 房东房源查询DTO（匹配Landlord_QueryMyHouse存储过程返回字段）
 */
@Data
public class LandlordHouseDTO {
    private String areaNo;        // 区域号（对应存储过程"区域号"）
    private String buildingNo;    // 楼栋号（对应存储过程"楼栋号"）
    private String roomNo;        // 房间号（对应存储过程"房间号"）
    private String roomType;      // 房间类型（对应存储过程"房间类型"）
    private BigDecimal rent;      // 月租金（对应存储过程"月租金"）
    private BigDecimal deposit;   // 押金（对应存储过程"押金"）
    private String roomStatus;    // 房源状态（对应存储过程"房源状态"）
}