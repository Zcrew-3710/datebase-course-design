package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 房东退租请求DTO（匹配LandlordCheckOut存储过程参数）
 */
@Data
public class LandlordCheckOutDTO {
    @NotNull(message = "合同ID不能为空")
    private Integer contractId;       // 合同ID（INT类型，必输）

    @NotBlank(message = "房东ID不能为空")
    private String landlordId;        // 房东ID（必输，校验合同归属）
}