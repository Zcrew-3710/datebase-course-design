package com.zufang.renthouse.mapper;

import com.zufang.renthouse.dto.AdminContractDTO;
import com.zufang.renthouse.dto.AdminLandlordDTO;
import com.zufang.renthouse.dto.AdminTenantDTO;
import com.zufang.renthouse.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理员Mapper（调用管理员相关存储过程）
 */
@Mapper
public interface AdminMapper {
    /**
     * 根据管理员ID查询是否存在
     * @param adminId 管理员ID
     * @return 存在则返回Administrator对象，不存在则返回null
     */
    @Select("SELECT adminId FROM Administrator WHERE adminId = #{adminId}")
    Administrator getAdminById(String adminId);
    /**
     * 调用QueryAllTenantInfo查询所有租户（支持排序）
     */
    @Select("EXEC QueryAllTenantInfo @SortType=#{sortType}")
    List<AdminTenantDTO> queryAllTenant(String sortType);
    /**
     * 调用QueryAllLandlordInfo查询所有房东（支持排序）
     */
    @Select("EXEC QueryAllLandlordInfo @SortType=#{sortType}")
    List<AdminLandlordDTO> queryAllLandlord(String sortType);

    /**
     * 调用QueryAllContractInfo查询所有合同（固定按合同编号倒序）
     */
    @Select("EXEC QueryAllContractInfo")
    @Results({
            @Result(column = "租期截止日期", property = "租期截止日期"),
            @Result(column = "剩余到期天数", property = "剩余到期天数")
    })
    List<AdminContractDTO> queryAllContract();
}