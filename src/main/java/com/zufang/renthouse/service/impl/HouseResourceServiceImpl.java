package com.zufang.renthouse.service.impl;

import com.zufang.renthouse.dto.HouseResponseDTO;
import com.zufang.renthouse.dto.LandlordHouseDTO;
import com.zufang.renthouse.dto.LandlordOfflineHouseDTO;
import com.zufang.renthouse.entity.HouseResource;
import com.zufang.renthouse.mapper.HouseResourceMapper;
import com.zufang.renthouse.mapper.LandlordMapper;
import com.zufang.renthouse.service.HouseResourceService;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 房源Service实现类（已存在，补充uploadHouse方法）
 */
@Service
public class HouseResourceServiceImpl implements HouseResourceService {

    @Autowired
    private HouseResourceMapper houseResourceMapper;
    @Autowired
    private LandlordMapper landlordMapper;

    @Override
    public Result<List<HouseResponseDTO>> queryHouseByLevel(String houseLevel) {
        if (!"优质".equals(houseLevel) && !"一般".equals(houseLevel)) {
            throw new IllegalArgumentException("房源等级只能输入 优质 或 一般！");
        }
        // 调用Mapper，返回HouseResponseDTO列表
        List<HouseResponseDTO> list = houseResourceMapper.queryHouseByLevel(houseLevel);
        return Result.success(list);
    }

//    @Override
//    public Result<List<HouseResource>> listAllHouse() {
//        List<HouseResource> list = houseResourceMapper.listAllHouse();
//        return Result.success(list);
//    }
@Override
public Result<List<HouseResponseDTO>> listAllHouse() {
    // 传null表示不筛选任何条件，存储过程返回所有房源
    List<HouseResponseDTO> houseList = houseResourceMapper.listAllHouseByProc(null, null, null);
    return Result.success(houseList);
}

    @Override
    public Result<?> updateRoomStatus(String areaNo, String buildingNo, String roomNo, String roomStatus) {
        houseResourceMapper.updateRoomStatus(areaNo, buildingNo, roomNo, roomStatus);
        return Result.success("房源状态更新成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> uploadHouse(HouseResource houseResource) {
        try {
            // 调用LandlordUploadHouse存储过程
            houseResourceMapper.uploadHouseByProc(
                    houseResource.getLandlordId(),
                    houseResource.getAreaNo(),
                    houseResource.getBuildingNo(),
                    houseResource.getRoomNo(),
                    houseResource.getRoomType(),
                    houseResource.getRent(),
                    houseResource.getDeposit()
            );
            return Result.success("房源上传成功", houseResource);
        } catch (Exception e) {
            return Result.error("房源上传失败：" + e.getMessage());
        }
    }


    /**
     * 房东下架房源（依赖存储过程的状态校验和删除逻辑）
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 兜底事务，保障删除操作原子性
    public Result<?> landlordOfflineHouse(LandlordOfflineHouseDTO dto) {
        try {
            // 调用存储过程下架房源
            houseResourceMapper.landlordOfflineHouse(dto);
            return Result.success("房源下架成功！", dto);
        } catch (DataAccessException e) {
            // 捕获存储过程抛出的业务错误（如房源已租）
            String errorMsg = e.getMessage();
            // 提取核心错误信息（去掉MyBatis包装的冗余内容）
            if (errorMsg.contains("错误：")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("错误："));
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            // 捕获系统异常（如数据库连接失败）
            return Result.error("房源下架失败：" + e.getMessage());
        }
    }

    // 原有查询房源、下架房源的方法保留不变...

    @Override
    public Result<List<LandlordHouseDTO>> queryLandlordMyHouse(String landlordId) {
        // 1. 基础参数校验：房东ID非空
        if (landlordId == null || landlordId.trim().isEmpty()) {
            return Result.paramError("房东ID不能为空！");
        }

        try {
            // 2. 调用存储过程查询房源
            List<LandlordHouseDTO> houseList = houseResourceMapper.queryLandlordMyHouse(landlordId);

            // 3. 处理无房源场景
            if (houseList.isEmpty()) {
                return Result.success("暂无名下房源", houseList);
            }
            return Result.success("查询成功", houseList);

        } catch (DataAccessException e) {
            // 捕获数据库访问异常
            return Result.error("房源查询失败：" + e.getMessage());
        } catch (Exception e) {
            // 捕获系统异常
            return Result.error("系统异常：" + e.getMessage());
        }
    }
}