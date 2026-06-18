package com.zufang.renthouse.mapper;

import com.zufang.renthouse.entity.Landlord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface LandlordMapper {
    // ========== 修正：接收Landlord实体参数 ==========新增房东
    @Insert("EXEC AddLandlord @FullName=#{landlord.fullName}, @IDCardNumber=#{landlord.idCardNumber}, @ContactPhone=#{landlord.contactPhone}, @ManagedBuildingNo=#{landlord.managedBuildingNo}")
    void addLandlordByProc(@Param("landlord") Landlord landlord);

    // 其他方法保留不变
    @Select("SELECT * FROM Landlord WHERE LandlordID = #{landlordId}")
    Landlord getLandlordById(String landlordId);

    @Select("SELECT * FROM Landlord WHERE IDCardNumber = #{idCardNumber}")
    Landlord getLandlordByIdCard(String idCardNumber);

    @Select("SELECT * FROM Landlord ORDER BY LandlordID ASC")
    List<Landlord> listAllLandlord();

    @Update("UPDATE Landlord SET FullName=#{fullName}, ContactPhone=#{contactPhone}, ManagedBuildingNo=#{managedBuildingNo} WHERE LandlordID = #{landlordId}")
    void updateLandlord(Landlord landlord);

    @Delete("DELETE FROM Landlord WHERE LandlordID = #{landlordId}")
    void deleteLandlord(String landlordId);

    /**
     * 房东：根据身份证号修改手机号
     */
    @Update("UPDATE Landlord SET ContactPhone = #{contactPhone} WHERE IDCardNumber = #{idCardNumber}")
    int updateLandlordPhoneByIdCard(@Param("idCardNumber") String idCardNumber,
                                    @Param("contactPhone") String contactPhone);

    /**
     * 房东：根据身份证号注销账户（删除记录）
     */
    @Delete("DELETE FROM Landlord WHERE IDCardNumber = #{idCardNumber}")
    int deleteLandlordByIdCard(@Param("idCardNumber") String idCardNumber);
}