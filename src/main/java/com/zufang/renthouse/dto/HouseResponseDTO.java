package com.zufang.renthouse.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 房源响应DTO（匹配前端要求的返回格式）
 */
@Data
public class HouseResponseDTO {
    private String areaNo;          // 小区/区域编号（如：阳光花苑）
    private String buildingNo;      // 楼栋号（如：1号楼）
    private String roomNo;          // 房间号（如：101）
    private String roomType;        // 户型（如：三室一厅精装修）
    private BigDecimal rent;        // 租金（保留2位小数）
    private BigDecimal deposit;     // zs押金（保留2位小数）
    private String roomStatus;      // 房源状态（空闲/已租）
    private String landlordId;      // 房东ID（如：L001）
    private String landlordName;    // 房东姓名（如：王哥）
    private Integer landlordCredit; // 房东信誉分（如：95）
}