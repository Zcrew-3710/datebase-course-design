package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 登录请求DTO（租客/房东通用）
 */
@Data
public class LoginDTO {
    @NotBlank(message = "姓名不能为空")
    private String fullName;

    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^\\d{17}[0-9Xx]$", message = "身份证号格式错误")
    private String idCardNumber;

    @NotBlank(message = "用户类型不能为空")
    @Pattern(regexp = "^TENANT|LANDLORD$", message = "用户类型只能是TENANT或LANDLORD")
    private String userType;
}
