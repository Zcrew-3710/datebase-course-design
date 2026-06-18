package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 注销账户请求DTO（租客/房东通用）
 */
@Data
public class DeleteAccountDTO {
    @NotBlank(message = "身份证号不能为空")
    private String idCardNumber; // 身份证号（唯一标识用户）
}