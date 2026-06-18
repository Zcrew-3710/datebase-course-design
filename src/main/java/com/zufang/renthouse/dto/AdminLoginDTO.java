package com.zufang.renthouse.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 管理员登录请求DTO（仅验证ID）
 */
@Data
public class AdminLoginDTO {
    @NotBlank(message = "管理员ID不能为空")
    private String adminId; // 登录传入的管理员ID
}