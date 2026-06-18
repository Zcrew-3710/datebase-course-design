package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 房东注册DTO
 */
@Data
public class LandlordRegisterDTO {
    @NotBlank(message = "姓名不能为空")
    private String fullName;

    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^\\d{17}[0-9Xx]$", message = "身份证号格式错误")
    private String idCardNumber;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String contactPhone;

    private String managedBuildingNo; // 管理的楼栋号（可选）
}