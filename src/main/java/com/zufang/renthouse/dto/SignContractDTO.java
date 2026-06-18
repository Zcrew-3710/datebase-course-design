package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 签订合同请求DTO（匹配SignContract存储过程参数）
 */
@Data
public class SignContractDTO {
    @NotBlank(message = "租客ID不能为空")
    private String tenantId;          // 租客ID（必输）

    @NotBlank(message = "房东ID不能为空")
    private String landlordId;        // 房东ID（必输）

    @NotBlank(message = "区域号不能为空")
    private String areaNo;            // 房源区域号（必输）

    @NotBlank(message = "楼栋号不能为空")
    private String buildingNo;        // 房源楼栋号（必输）

    @NotBlank(message = "房间号不能为空")
    private String roomNo;            // 房源房间号（必输）

    @NotNull(message = "租期截止日期不能为空")
    private LocalDate leaseExpiryDate;// 租期截止日期（必输）
}