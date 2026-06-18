package com.zufang.renthouse.service.impl;

import com.zufang.renthouse.dto.AdminContractDTO;
import com.zufang.renthouse.dto.AdminLandlordDTO;
import com.zufang.renthouse.dto.AdminLoginDTO;
import com.zufang.renthouse.dto.AdminTenantDTO;
import com.zufang.renthouse.entity.Administrator;
import com.zufang.renthouse.mapper.AdminMapper;
import com.zufang.renthouse.service.AdminService;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员Service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result<List<AdminTenantDTO>> queryAllTenant(String sortType) {
        // 1. 默认排序类型为ID
        if (sortType == null || sortType.trim().isEmpty()) {
            sortType = "ID";
        }
        // 2. 校验排序类型合法性
        if (!"ID".equals(sortType) && !"Credit".equals(sortType)) {
            return Result.error("排序类型错误！仅支持ID（按ID倒序）或Credit（按信誉分倒序）");
        }

        try {
            List<AdminTenantDTO> tenantList = adminMapper.queryAllTenant(sortType);
            return Result.success("租户列表查询成功", tenantList);
        } catch (DataAccessException e) {
            return Result.error("租户列表查询失败：" + e.getMessage());
        }
    }

    @Override
    public Result<List<AdminLandlordDTO>> queryAllLandlord(String sortType) {
        // 1. 默认排序类型为ID
        if (sortType == null || sortType.trim().isEmpty()) {
            sortType = "ID";
        }
        // 2. 校验排序类型合法性
        if (!"ID".equals(sortType) && !"Credit".equals(sortType)) {
            return Result.error("排序类型错误！仅支持ID（按ID倒序）或Credit（按信誉分倒序）");
        }

        try {
            List<AdminLandlordDTO> landlordList = adminMapper.queryAllLandlord(sortType);
            return Result.success("房东列表查询成功", landlordList);
        } catch (DataAccessException e) {
            return Result.error("房东列表查询失败：" + e.getMessage());
        }
    }

    @Override
    public Result<List<AdminContractDTO>> queryAllContract() {
        try {
            List<AdminContractDTO> contractList = adminMapper.queryAllContract();
            return Result.success("合同列表查询成功", contractList);
        } catch (DataAccessException e) {
            return Result.error("合同列表查询失败：" + e.getMessage());
        }
    }

    /**
     * 管理员Service实现类
     */
    @Override
    public Result<?> login(AdminLoginDTO dto) {
        try {
            // 1. 查询数据库是否存在该管理员ID
            Administrator admin = adminMapper.getAdminById(dto.getAdminId());

            // 2. 验证结果：存在则登录成功，不存在则失败
            if (admin != null) {
                return Result.success("登录成功！", admin);
            } else {
                return Result.error("登录失败：管理员ID不存在！");
            }
        } catch (Exception e) {
            // 捕获数据库异常，返回友好提示
            return Result.error("登录异常：" + e.getMessage());
        }
    }
}