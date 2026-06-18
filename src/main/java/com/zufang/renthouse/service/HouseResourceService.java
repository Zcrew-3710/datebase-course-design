package com.zufang.renthouse.service;

import com.zufang.renthouse.dto.HouseResponseDTO;
import com.zufang.renthouse.dto.LandlordHouseDTO;
import com.zufang.renthouse.dto.LandlordOfflineHouseDTO;
import com.zufang.renthouse.entity.HouseResource;
import com.zufang.renthouse.utils.Result;
import java.util.List;

/**
 * 房源Service接口（已存在，补充uploadHouse方法）
 */
public interface HouseResourceService {
    /**
     * 按等级查询房源（返回含房东信息的HouseResponseDTO）
     */
    Result<List<HouseResponseDTO>> queryHouseByLevel(String houseLevel);

    Result<List<HouseResponseDTO>> listAllHouse();


    Result<?> updateRoomStatus(String areaNo, String buildingNo, String roomNo, String roomStatus);

    // 新增：房东上传房源（调用LandlordUploadHouse存储过程）
    Result<?> uploadHouse(HouseResource houseResource);

    /**
     * 房东下架房源（校验房源状态，非已租才可删除）
     * @param dto 下架房源的请求参数
     * @return 操作结果
     */
    Result<?> landlordOfflineHouse(LandlordOfflineHouseDTO dto);

    /**
     * 房东查询本人名下所有房源
     * @param landlordId 房东ID
     * @return 房源列表
     */
    Result<List<LandlordHouseDTO>> queryLandlordMyHouse(String landlordId);
}