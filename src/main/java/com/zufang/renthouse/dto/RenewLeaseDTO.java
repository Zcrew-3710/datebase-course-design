package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 租客续租请求DTO（匹配RenewLease存储过程参数）
 */
@Data
public class RenewLeaseDTO {
    @NotNull(message = "合同ID不能为空")
    private Integer contractId;       // 原合同ID（INT类型，必输）

    @NotNull(message = "新租期截止日期不能为空")
    private LocalDate newExpiryDate;  // 新截止日期（必输，需晚于原日期）
}