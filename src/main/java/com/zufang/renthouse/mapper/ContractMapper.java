package com.zufang.renthouse.mapper;

import com.zufang.renthouse.dto.*;
import com.zufang.renthouse.entity.Contract;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ContractMapper {

//    /**
//     * 调用存储过程退租处理
//     */
//    @Delete("EXEC ReturnHouse @ContractID=#{contractId}, @ReturnType=#{returnType}")
//    void returnHouseByProc(@Param("contractId") Integer contractId, @Param("returnType") String returnType);

    /**
     * 根据合同ID查询合同
     */
    @Select("SELECT * FROM Contract WHERE ContractID = #{contractId}")
    Contract getContractById(Integer contractId);

    /**
     * 查询所有合同
     */
    @Select("SELECT * FROM Contract")
    List<Contract> listAllContract();

    /**
     * 更新合同违约状态
     */
    @Update("UPDATE Contract SET TenantBreachStatus = #{status} WHERE ContractID = #{contractId}")
    void updateTenantBreachStatus(@Param("contractId") Integer contractId, @Param("status") Integer status);

    /**
     * 合同Mapper（对接Contract表和存储过程）
     */

        /**
         * 调用存储过程TenantQueryOwnContract，查询租客本人合同
         * @param tenantId 租客ID（如T001）
         * @return 合同列表（含甲方/乙方/房源/合同信息）
         */
        @Select("EXEC TenantQueryOwnContract @TenantID=#{tenantId}")
        List<ContractResponseDTO> queryOwnContract(String tenantId);


    /**
     * 调用存储过程SignContract签订合同
     * @param dto 签订合同的请求参数
     */
    @Select("EXEC SignContract " +
            "@TenantID=#{tenantId}, " +
            "@LandlordID=#{landlordId}, " +
            "@AreaNo=#{areaNo}, " +
            "@BuildingNo=#{buildingNo}, " +
            "@RoomNo=#{roomNo}, " +
            "@LeaseExpiryDate=#{leaseExpiryDate}")
    void signContract(SignContractDTO dto);


    /**
     * 调用存储过程TenantCheckOut处理租客退租
     * @param dto 退租请求参数（合同ID+租客ID）
     */
    @Select("EXEC TenantCheckOut " +
            "@ContractID=#{contractId}, " +
            "@TenantID=#{tenantId}")
    void tenantCheckOut(TenantCheckOutDTO dto);


    /**
     * 调用存储过程LandlordQueryOwnContract，查询房东本人合同
     * @param landlordId 房东ID（如L001）
     * @return 合同列表（含甲方/乙方/房源/合同信息）
     */
    @Select("EXEC LandlordQueryOwnContract @LandlordID=#{landlordId}")
    @Results({
            // 复用和租客查询一致的字段映射（列名完全匹配）
            @Result(column = "甲方姓名", property = "甲方姓名"),
            @Result(column = "甲方身份证号", property = "甲方身份证号"),
            @Result(column = "甲方联系方式", property = "甲方联系方式"),
            @Result(column = "乙方姓名", property = "乙方姓名"),
            @Result(column = "乙方身份证号", property = "乙方身份证号"),
            @Result(column = "乙方联系方式", property = "乙方联系方式"),
            @Result(column = "小区号", property = "小区号"),
            @Result(column = "楼栋号", property = "楼栋号"),
            @Result(column = "房间号", property = "房间号"),
            @Result(column = "房间类型", property = "房间类型"),
            @Result(column = "租金", property = "租金"),
            @Result(column = "押金", property = "押金"),
            @Result(column = "租赁期限截止日期", property = "租赁期限截止日期"),
            @Result(column = "到期天数", property = "到期天数"),
            @Result(column = "合同编号", property = "合同编号")
    })
    List<ContractResponseDTO> queryLandlordOwnContract(String landlordId);

    /**
     * 调用存储过程LandlordCheckOut处理房东退租
     * @param dto 退租请求参数（合同ID+房东ID）
     */
    @Select("EXEC LandlordCheckOut " +
            "@ContractID=#{contractId}, " +
            "@LandlordID=#{landlordId}")
    void landlordCheckOut(LandlordCheckOutDTO dto);

    /**
     * 调用存储过程RenewLease处理租客续租（仅更新合同截止日期）
     * @param dto 续租请求参数（原合同ID+新截止日期）
     */
    @Select("EXEC RenewLease " +
            "@ContractID=#{contractId}, " +
            "@NewExpiryDate=#{newExpiryDate}")
    void renewLease(RenewLeaseDTO dto);
}
