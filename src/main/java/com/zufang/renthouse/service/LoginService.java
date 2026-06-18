package com.zufang.renthouse.service;

import com.zufang.renthouse.dto.LoginDTO;
import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.entity.Tenant;
import com.zufang.renthouse.mapper.LandlordMapper;
import com.zufang.renthouse.mapper.TenantMapper;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录业务逻辑
 */
@Service
public class LoginService {

    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private LandlordMapper landlordMapper;

    /**
     * 租客/房东登录
     */
    public Result<?> login(LoginDTO loginDTO) {
        // 租客登录
        if ("TENANT".equals(loginDTO.getUserType())) {
            // 根据身份证号查询租客
            Tenant tenant = tenantMapper.getTenantByIdCard(loginDTO.getIdCardNumber());
            if (tenant == null) {
                return Result.error("租客不存在");
            }
            // 校验姓名是否匹配
            if (!tenant.getFullName().equals(loginDTO.getFullName())) {
                return Result.error("姓名错误");
            }
            return Result.success(tenant);
        }
        // 房东登录
        else if ("LANDLORD".equals(loginDTO.getUserType())) {
            // 根据身份证号查询房东
            Landlord landlord = landlordMapper.getLandlordByIdCard(loginDTO.getIdCardNumber());
            if (landlord == null) {
                return Result.error("房东不存在");
            }
            // 校验姓名是否匹配
            if (!landlord.getFullName().equals(loginDTO.getFullName())) {
                return Result.error("姓名错误");
            }
            return Result.success(landlord);
        } else {
            return Result.paramError("用户类型只能是TENANT或LANDLORD");
        }
    }
}