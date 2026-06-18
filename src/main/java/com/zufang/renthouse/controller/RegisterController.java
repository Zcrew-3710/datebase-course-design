package com.zufang.renthouse.controller;

import com.zufang.renthouse.dto.LandlordRegisterDTO;
import com.zufang.renthouse.dto.TenantRegisterDTO;
import com.zufang.renthouse.service.RegisterService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册接口（租客+房东）
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 租客注册
     */
    @PostMapping("/tenant")
    public Result<?> registerTenant(@Valid @RequestBody TenantRegisterDTO registerDTO) {
        return registerService.registerTenant(registerDTO);
    }

    /**
     * 房东注册
     */
    @PostMapping("/landlord")
    public Result<?> registerLandlord(@Valid @RequestBody LandlordRegisterDTO registerDTO) {
        return registerService.registerLandlord(registerDTO);
    }
}