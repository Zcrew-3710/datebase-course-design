package com.zufang.renthouse.controller;

import com.zufang.renthouse.dto.LoginDTO;
import com.zufang.renthouse.service.LoginService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 租客/房东登录
     */
    @PostMapping
    public Result<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        // 1. 先检查参数验证错误
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "参数错误";
            return Result.paramError(errorMessage);
        }

        // 2. 调用登录服务
        return loginService.login(loginDTO);
    }
}