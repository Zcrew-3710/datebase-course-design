package com.zufang.renthouse.mapper;

import com.zufang.renthouse.dto.HouseResponseDTO;
import com.zufang.renthouse.dto.LandlordHouseDTO;
import com.zufang.renthouse.dto.LandlordOfflineHouseDTO;
import com.zufang.renthouse.entity.HouseResource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.math.BigDecimal;
@Mapper
public interface HouseResourceMapper {
    /**
     * 根据联合主键查询房源
     */
    @Select("SELECT * FROM HouseResource WHERE AreaNo = #{areaNo} AND BuildingNo = #{buildingNo} AND RoomNo = #{roomNo}")
    HouseResource getHouseByPK(
            @Param("areaNo") String areaNo,
            @Param("buildingNo") String buildingNo,
            @Param("roomNo") String roomNo);

    /**
     * 更新房源状态
     */
    @Update("UPDATE HouseResource SET RoomStatus = #{roomStatus} WHERE AreaNo = #{areaNo} AND BuildingNo = #{buildingNo} AND RoomNo = #{roomNo}")
    void updateRoomStatus(
            @Param("areaNo") String areaNo,
            @Param("buildingNo") String buildingNo,
            @Param("roomNo") String roomNo,
            @Param("roomStatus") String roomStatus);

//    /**
//     * 调用存储过程按等级查询房源
//     */
//    @Select("EXEC QueryHouseByLevel @HouseLevel=#{houseLevel}")
//    List<HouseLevelVO> queryHouseByLevel(String houseLevel);

    /**
     * 调用修改后的QueryHouseByLevel存储过程，返回含房东信息的HouseResponseDTO
     */
    @Select("EXEC QueryHouseByLevel @HouseLevel=#{houseLevel}")
    @Results({
            // 映射存储过程返回的字段到HouseResponseDTO（关键：列名和属性名一一对应）
            @Result(column = "AreaNo", property = "areaNo"),          // 房源区域
            @Result(column = "BuildingNo", property = "buildingNo"),  // 楼栋号
            @Result(column = "RoomNo", property = "roomNo"),          // 房间号
            @Result(column = "RoomType", property = "roomType"),      // 户型类型
            @Result(column = "Rent", property = "rent"),              // 租金
            @Result(column = "Deposit", property = "deposit"),        // 押金
            @Result(column = "RoomStatus", property = "roomStatus"),  // 房源状态
            @Result(column = "LandlordID", property = "landlordId"),  // 房东ID
            @Result(column = "房东姓名", property = "landlordName"),  // 房东姓名（中文列名）
            @Result(column = "房东信誉分", property = "landlordCredit")// 房东信誉分（中文列名）
    })
    List<HouseResponseDTO> queryHouseByLevel(String houseLevel);

//    /**
//     * 查询所有房源
//     */
//    @Select("SELECT * FROM HouseResource")
//    List<HouseResource> listAllHouse();

    /**
     * 调用存储过程QueryHouseWithLandlordCredit查询所有房源（含房东信誉分）
     * 不传参数时，存储过程的@AreaNo/@BuildingNo/@RoomStatus均为NULL，返回所有房源
     */
    @Select("EXEC QueryHouseWithLandlordCredit @AreaNo=#{areaNo}, @BuildingNo=#{buildingNo}, @RoomStatus=#{roomStatus}")
    @Results({
            // 映射存储过程返回的字段到HouseResponseDTO的属性（关键：字段名对应）
            @Result(column = "房源区域", property = "areaNo"),
            @Result(column = "房源楼栋", property = "buildingNo"),
            @Result(column = "房源房间", property = "roomNo"),
            @Result(column = "户型类型", property = "roomType"),
            @Result(column = "月租金", property = "rent"),
            @Result(column = "押金金额", property = "deposit"),
            @Result(column = "房源状态", property = "roomStatus"),
            @Result(column = "房东ID", property = "landlordId"),
            @Result(column = "房东姓名", property = "landlordName"),
            @Result(column = "房东信誉分", property = "landlordCredit"),
    })
    List<HouseResponseDTO> listAllHouseByProc(
            @Param("areaNo") String areaNo,
            @Param("buildingNo") String buildingNo,
            @Param("roomStatus") String roomStatus
    );

    /**
     * 调用LandlordUploadHouse存储过程上传房源
     */
    @Insert("EXEC LandlordUploadHouse @LandlordID=#{landlordId}, @AreaNo=#{areaNo}, @BuildingNo=#{buildingNo}, @RoomNo=#{roomNo}, @RoomType=#{roomType}, @Rent=#{rent}, @Deposit=#{deposit}")
    void uploadHouseByProc(@Param("landlordId") String landlordId,
                           @Param("areaNo") String areaNo,
                           @Param("buildingNo") String buildingNo,
                           @Param("roomNo") String roomNo,
                           @Param("roomType") String roomType,
                           @Param("rent") BigDecimal rent,
                           @Param("deposit") BigDecimal deposit);



    /**
     * 调用存储过程Landlord_OfflineHouse下架房源
     * @param dto 下架房源的请求参数
     */
    @Select("EXEC Landlord_OfflineHouse " +
            "@LandlordID=#{landlordId}, " +
            "@AreaNo=#{areaNo}, " +
            "@BuildingNo=#{buildingNo}, " +
            "@RoomNo=#{roomNo}")
    void landlordOfflineHouse(LandlordOfflineHouseDTO dto);

    /**
     * 调用存储过程Landlord_QueryMyHouse查询房东本人房源
     * @param landlordId 房东ID
     * @return 房东名下房源列表
     */
    @Select("EXEC Landlord_QueryMyHouse @LandlordID=#{landlordId}")
    @Results({
            @Result(column = "区域号", property = "areaNo"),
            @Result(column = "楼栋号", property = "buildingNo"),
            @Result(column = "房间号", property = "roomNo"),
            @Result(column = "房间类型", property = "roomType"),
            @Result(column = "月租金", property = "rent"),
            @Result(column = "押金", property = "deposit"),
            @Result(column = "房源状态", property = "roomStatus")
    })
    List<LandlordHouseDTO> queryLandlordMyHouse(String landlordId);
}
