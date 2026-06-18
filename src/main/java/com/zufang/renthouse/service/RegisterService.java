package com.zufang.renthouse.service;

import com.zufang.renthouse.dto.LandlordRegisterDTO;
import com.zufang.renthouse.dto.TenantRegisterDTO;
import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.entity.Tenant;
import com.zufang.renthouse.mapper.LandlordMapper;
import com.zufang.renthouse.mapper.TenantMapper;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册业务逻辑（租客+房东）- 修正参数匹配问题
 */
@Service
public class RegisterService {

    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private LandlordMapper landlordMapper;

    /**
     * 租客注册（调用新增租客存储过程）
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<?> registerTenant(TenantRegisterDTO registerDTO) {
        // 校验身份证号唯一性
        if (tenantMapper.getTenantByIdCard(registerDTO.getIdCardNumber()) != null) {
            return Result.error("身份证号已注册");
        }
        // 转换为Tenant实体（信用分默认80，触发器强制）
        Tenant tenant = new Tenant();
        tenant.setFullName(registerDTO.getFullName());
        tenant.setIdCardNumber(registerDTO.getIdCardNumber());
        tenant.setContactPhone(registerDTO.getContactPhone());
        tenant.setGender(registerDTO.getGender());
        tenant.setCreditScore(80); // 触发器会强制覆盖为80

        // 调用存储过程新增租客（现在Mapper支持接收实体）
        tenantMapper.addTenantByProc(tenant);

        // 查询新增后的租客信息（返回给前端，包含自动生成的tenantId）
        Tenant newTenant = tenantMapper.getTenantByIdCard(registerDTO.getIdCardNumber());
        return Result.success("租客注册成功", newTenant);
    }

    /**
     * 房东注册（新增房东，复用触发器强制信用分80）
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<?> registerLandlord(LandlordRegisterDTO registerDTO) {
        // 校验身份证号唯一性
        if (landlordMapper.getLandlordByIdCard(registerDTO.getIdCardNumber()) != null) {
            return Result.error("身份证号已注册");
        }
        // 转换为Landlord实体
        Landlord landlord = new Landlord();
        landlord.setFullName(registerDTO.getFullName());
        landlord.setIdCardNumber(registerDTO.getIdCardNumber());
        landlord.setContactPhone(registerDTO.getContactPhone());
        landlord.setManagedBuildingNo(registerDTO.getManagedBuildingNo());
        landlord.setCreditScore(80); // 触发器强制覆盖为80

        // 调用存储过程新增房东（Mapper已适配实体参数）
        landlordMapper.addLandlordByProc(landlord);

        // 查询新增后的房东信息（返回自动生成的landlordId）
        Landlord newLandlord = landlordMapper.getLandlordByIdCard(registerDTO.getIdCardNumber());
        return Result.success("房东注册成功", newLandlord);
    }
}