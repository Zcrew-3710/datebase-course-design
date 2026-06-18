package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 租客退租请求DTO（匹配TenantCheckOut存储过程参数）
 */
@Data
public class TenantCheckOutDTO {
    @NotNull(message = "合同ID不能为空")
    private Integer contractId;       // 合同ID（INT类型，必输）

    @NotBlank(message = "租客ID不能为空")
    private String tenantId;          // 租客ID（必输，校验合同归属）
}