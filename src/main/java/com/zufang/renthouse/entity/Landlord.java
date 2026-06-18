package com.zufang.renthouse.entity;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class Landlord {
    private String landlordId; // 自动生成（L001/L002...）

    @NotBlank(message = "房东姓名不能为空")
    private String fullName;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String contactPhone;

    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^\\d{17}[0-9Xx]$", message = "身份证号格式错误")
    private String idCardNumber;

    private Integer creditScore; // 触发器强制设为80

    @NotBlank(message = "管理楼栋号不能为空")
    private String managedBuildingNo; // 新增：适配存储过程的必输参数
}
