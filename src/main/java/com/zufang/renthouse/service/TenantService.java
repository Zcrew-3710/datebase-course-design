package com.zufang.renthouse.service;

import com.zufang.renthouse.entity.Tenant;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;
import com.zufang.renthouse.utils.Result;
import java.util.List;

public interface TenantService {
    /**
     * 新增租客（调用存储过程）
     */
    void addTenant(Tenant tenant);

    /**
     * 根据身份证号查询租客
     */
    Tenant getTenantByIdCard(String idCardNumber);

    /**
     * 查询所有租客
     */
    List<Tenant> listAllTenant();

    /**
     * 租客修改手机号
     */
    Result<?> updatePhone(UpdatePhoneDTO updatePhoneDTO);

    /**
     * 租客注销账户
     */
    Result<?> deleteAccount(DeleteAccountDTO deleteAccountDTO);
}

