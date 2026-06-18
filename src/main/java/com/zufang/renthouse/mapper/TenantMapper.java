package com.zufang.renthouse.mapper;

import com.zufang.renthouse.entity.Tenant;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TenantMapper {
    /**
     * 调用存储过程新增租客
     */

    // ========== 新增：接收Tenant实体参数 ==========
    @Insert("EXEC AddTenant @FullName=#{tenant.fullName}, @IDCardNumber=#{tenant.idCardNumber}, @ContactPhone=#{tenant.contactPhone}, @Gender=#{tenant.gender}, @CreditScore=#{tenant.creditScore}")
    void addTenantByProc(@Param("tenant") Tenant tenant);
    /**
     * 根据身份证号查询租客
     */
    @Select("SELECT * FROM Tenant WHERE IDCardNumber = #{idCardNumber}")
    Tenant getTenantByIdCard(String idCardNumber);

    /**
     * 查询所有租客
     */
    @Select("SELECT * FROM Tenant")
    List<Tenant> listAllTenant();

    /**
     * 更新租客信用分
     */
    @Update("UPDATE Tenant SET CreditScore = #{creditScore} WHERE TenantID = #{tenantId}")
    void updateCreditScore(@Param("tenantId") String tenantId, @Param("creditScore") Integer creditScore);

    /**
     * 租客：根据身份证号修改手机号
     */
    @Update("UPDATE Tenant SET ContactPhone = #{contactPhone} WHERE IDCardNumber = #{idCardNumber}")
    int updateTenantPhoneByIdCard(@Param("idCardNumber") String idCardNumber,
                                  @Param("contactPhone") String contactPhone);

    /**
     * 租客：根据身份证号注销账户（删除记录）
     */
    @Delete("DELETE FROM Tenant WHERE IDCardNumber = #{idCardNumber}")
    int deleteTenantByIdCard(@Param("idCardNumber") String idCardNumber);

}