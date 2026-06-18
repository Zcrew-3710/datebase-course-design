package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 修改手机号请求DTO（租客/房东通用）
 */
@Data
public class UpdatePhoneDTO {
    @NotBlank(message = "身份证号不能为空")
    private String idCardNumber; // 身份证号（唯一标识用户）

    @NotBlank(message = "新手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String contactPhone; // 新手机号
}