package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 房东下架房源请求DTO（匹配Landlord_OfflineHouse存储过程参数）
 */
@Data
public class LandlordOfflineHouseDTO {
    @NotBlank(message = "房东ID不能为空")
    private String landlordId;        // 房东ID（必输）

    @NotBlank(message = "房源区域号不能为空")
    private String areaNo;            // 房源区域号（必输）

    @NotBlank(message = "房源楼栋号不能为空")
    private String buildingNo;        // 房源楼栋号（必输）

    @NotBlank(message = "房源房间号不能为空")
    private String roomNo;            // 房源房间号（必输）
}