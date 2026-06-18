package com.zufang.renthouse.controller;

import com.zufang.renthouse.entity.Tenant;
import com.zufang.renthouse.service.TenantService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;
import com.zufang.renthouse.service.TenantService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * 新增租客
     */
    @PostMapping("/add")
    public Result<Void> addTenant(@Valid @RequestBody Tenant tenant) {
        try {
            tenantService.addTenant(tenant);
            return Result.success();
        } catch (Exception e) {
            return Result.error("新增租客失败：" + e.getMessage());
        }
    }

    /**
     * 根据身份证号查询租客
     */
    @GetMapping("/getByIdCard")
    public Result<Tenant> getTenantByIdCard(@RequestParam String idCardNumber) {
        try {
            Tenant tenant = tenantService.getTenantByIdCard(idCardNumber);
            return Result.success(tenant);
        } catch (Exception e) {
            return Result.error("查询租客失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有租客
     */
    @GetMapping("/listAll")
    public Result<List<Tenant>> listAllTenant() {
        try {
            List<Tenant> list = tenantService.listAllTenant();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error("查询租客列表失败：" + e.getMessage());
        }
    }

    /**
     * 租客修改手机号
     * 请求URL：PUT http://localhost:8080/zufang/api/tenant/updatePhone
     */
    @PutMapping("/updatePhone")
    public Result<?> updatePhone(@Valid @RequestBody UpdatePhoneDTO updatePhoneDTO) {
        return tenantService.updatePhone(updatePhoneDTO);
    }

    /**
     * 租客注销账户
     * 请求URL：DELETE http://localhost:8080/zufang/api/tenant/deleteAccount
     * 注意：DELETE请求带JSON请求体，前端需适配（Axios支持）
     */
    @DeleteMapping("/deleteAccount")
    public Result<?> deleteAccount(@Valid @RequestBody DeleteAccountDTO deleteAccountDTO) {
        return tenantService.deleteAccount(deleteAccountDTO);
    }

}
