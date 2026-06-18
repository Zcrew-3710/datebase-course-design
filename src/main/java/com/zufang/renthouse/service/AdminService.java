package com.zufang.renthouse.service;

import com.zufang.renthouse.dto.AdminContractDTO;
import com.zufang.renthouse.dto.AdminLandlordDTO;
import com.zufang.renthouse.dto.AdminLoginDTO;
import com.zufang.renthouse.dto.AdminTenantDTO;
import com.zufang.renthouse.utils.Result;

import java.util.List;

/**
 * 管理员Service接口
 */
public interface AdminService {
    /**
     * 管理员Service接口
     */
        /**
         * 管理员登录（仅验证ID是否存在）
         * @param dto 登录请求参数（管理员ID）
         * @return 登录结果
         */
        Result<?> login(AdminLoginDTO dto);
    /**
     * 管理员查询所有租户
     * @param sortType 排序类型（ID/Credit，默认ID）
     */
    Result<List<AdminTenantDTO>> queryAllTenant(String sortType);

    /**
     * 管理员查询所有房东
     * @param sortType 排序类型（ID/Credit，默认ID）
     */
    Result<List<AdminLandlordDTO>> queryAllLandlord(String sortType);

    /**
     * 管理员查询所有合同
     */
    Result<List<AdminContractDTO>> queryAllContract();
}