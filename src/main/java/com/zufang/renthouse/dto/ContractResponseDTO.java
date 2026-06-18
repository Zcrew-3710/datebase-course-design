package com.zufang.renthouse.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 租客合同查询响应DTO（匹配TenantQueryOwnContract存储过程返回字段）
 */
@Data
public class ContractResponseDTO {
    // 甲方（房东）信息
    private String 甲方姓名;
    private String 甲方身份证号;
    private String 甲方联系方式;

    // 乙方（租客）信息
    private String 乙方姓名;
    private String 乙方身份证号;
    private String 乙方联系方式;

    // 房源信息
    private String 小区号;
    private String 楼栋号;
    private String 房间号;
    private String 房间类型;
    private BigDecimal 租金;
    private BigDecimal 押金;

    // 合同信息
    private Date 租赁期限截止日期;
    private Integer 到期天数;
    private String 合同编号;
}