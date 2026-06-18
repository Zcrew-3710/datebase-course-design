package com.zufang.renthouse.controller;

import com.zufang.renthouse.dto.AdminContractDTO;
import com.zufang.renthouse.dto.AdminLandlordDTO;
import com.zufang.renthouse.dto.AdminLoginDTO;
import com.zufang.renthouse.dto.AdminTenantDTO;
import com.zufang.renthouse.service.AdminService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员Controller
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员查询所有租户
     * 请求URL：GET /api/admin/tenant/list?sortType=Credit
     */
    @GetMapping("/tenant/list")
    public Result<List<AdminTenantDTO>> queryAllTenant(
            @RequestParam(required = false) String sortType) {
        return adminService.queryAllTenant(sortType);
    }

    /**
     * 管理员查询所有房东
     * 请求URL：GET /api/admin/landlord/list?sortType=Credit
     */
    @GetMapping("/landlord/list")
    public Result<List<AdminLandlordDTO>> queryAllLandlord(
            @RequestParam(required = false) String sortType) {
        return adminService.queryAllLandlord(sortType);
    }

    /**
     * 管理员查询所有合同
     * 请求URL：GET /api/admin/contract/list
     */
    @GetMapping("/contract/list")
    public Result<List<AdminContractDTO>> queryAllContract() {
        return adminService.queryAllContract();
    }

    /**
     * 管理员控制器
     */

        /**
         * 管理员登录（仅验证ID）
         * 请求URL：POST http://localhost:8080/zufang/api/admin/login
         * 请求体：AdminLoginDTO的JSON格式
         */
        @PostMapping("/login")
        public Result<?> login(@Valid @RequestBody AdminLoginDTO dto) {
            return adminService.login(dto);
        }

}