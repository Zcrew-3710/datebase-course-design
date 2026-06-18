package com.zufang.renthouse.service.impl;

import com.zufang.renthouse.entity.Tenant;
import com.zufang.renthouse.mapper.TenantMapper;
import com.zufang.renthouse.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;
import com.zufang.renthouse.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public void addTenant(Tenant tenant) {
        // 存储过程已做参数校验，直接调用
        tenantMapper.addTenantByProc(tenant);
    }
    @Override
    public Tenant getTenantByIdCard(String idCardNumber) {
        return tenantMapper.getTenantByIdCard(idCardNumber);
    }
    @Override
    public List<Tenant> listAllTenant() {
        return tenantMapper.listAllTenant();
    }

    /**
     * 租客修改手机号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updatePhone(UpdatePhoneDTO updatePhoneDTO) {
        // 1. 校验租客是否存在
        Tenant tenant = tenantMapper.getTenantByIdCard(updatePhoneDTO.getIdCardNumber());
        if (tenant == null) {
            return Result.error("租客不存在，无法修改手机号");
        }
        // 2. 修改手机号
        int rows = tenantMapper.updateTenantPhoneByIdCard(
                updatePhoneDTO.getIdCardNumber(),
                updatePhoneDTO.getContactPhone()
        );
        if (rows > 0) {
            // 3. 返回修改后的租客信息
            tenant.setContactPhone(updatePhoneDTO.getContactPhone());
            return Result.success("手机号修改成功", tenant);
        } else {
            return Result.error("手机号修改失败");
        }
    }

    /**
     * 租客注销账户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteAccount(DeleteAccountDTO deleteAccountDTO) {
        // 1. 校验租客是否存在
        Tenant tenant = tenantMapper.getTenantByIdCard(deleteAccountDTO.getIdCardNumber());
        if (tenant == null) {
            return Result.error("租客不存在，无法注销账户");
        }
        // 2. 注销账户（删除记录）
        int rows = tenantMapper.deleteTenantByIdCard(deleteAccountDTO.getIdCardNumber());
        if (rows > 0) {
            return Result.success("账户注销成功");
        } else {
            return Result.error("账户注销失败");
        }
    }
}
