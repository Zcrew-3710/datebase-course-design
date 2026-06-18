package com.zufang.renthouse.controller;

import com.zufang.renthouse.dto.LandlordHouseDTO;
import com.zufang.renthouse.dto.LandlordOfflineHouseDTO;
import jakarta.validation.Valid;
import com.zufang.renthouse.entity.HouseResource;
import com.zufang.renthouse.service.HouseResourceService;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.zufang.renthouse.dto.HouseResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house")
public class HouseResourceController {

    @Autowired
    private HouseResourceService houseResourceService;

//    /**
//     * 按等级查询房源
//     * 修正：返回值从 List<HouseLevelVO> → Result<List<HouseLevelVO>>
//     */
//    @GetMapping("/queryByLevel")
//    public Result<List<HouseLevelVO>> queryHouseByLevel(@RequestParam String houseLevel) {
//        // 直接返回Service的Result结果（无需拆包）
//        return houseResourceService.queryHouseByLevel(houseLevel);
//    }
    /**
     * 按等级查询房源（返回含房东信誉分的DTO）
     * 请求URL：GET http://localhost:8080/zufang/api/house/queryByLevel?houseLevel=优质
     */
    @GetMapping("/queryByLevel")
    public Result<List<HouseResponseDTO>> queryHouseByLevel(@RequestParam String houseLevel) {
        try {
            return houseResourceService.queryHouseByLevel(houseLevel);
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        }
    }


//    /**
//     * 查询所有房源
//     * 修正：返回值从 List<HouseResource> → Result<List<HouseResource>>
//     */
//    @GetMapping("/listAll")
//    public Result<List<HouseResource>> listAllHouse() {
//        return houseResourceService.listAllHouse();
//    }
    /**
     * 查询所有房源（修正：返回Result<List<HouseResponseDTO>>，含房东信誉分）
     * 请求URL：GET http://localhost:8080/zufang/api/house/listAll
     */
    @GetMapping("/listAll")
    public Result<List<HouseResponseDTO>> listAllHouse() {
        return houseResourceService.listAllHouse();
    }


    /**
     * 更新房源状态
     */
    @PutMapping("/updateStatus")
    public Result<?> updateRoomStatus(
            @RequestParam String areaNo,
            @RequestParam String buildingNo,
            @RequestParam String roomNo,
            @RequestParam String roomStatus
    ) {
        return houseResourceService.updateRoomStatus(areaNo, buildingNo, roomNo, roomStatus);
    }

    /**
     * 房东上传房源
     */
    @PostMapping("/upload")
    public Result<?> uploadHouse(@Valid @RequestBody HouseResource houseResource) {
        return houseResourceService.uploadHouse(houseResource);
    }

    /**
     * 房东下架房源
     * 请求URL：DELETE http://localhost:8080/zufang/api/house/landlord/offline
     * 请求体：LandlordOfflineHouseDTO的JSON格式
     */
    @DeleteMapping("/landlord/offline")
    public Result<?> landlordOfflineHouse(@Valid @RequestBody LandlordOfflineHouseDTO dto) {
        return houseResourceService.landlordOfflineHouse(dto);
    }

    /**
     * 房东查询本人名下所有房源
     * 请求URL：GET http://localhost:8080/zufang/api/house/landlord/my
     * 请求参数：landlordId（房东ID，如L001）
     */
    @GetMapping("/landlord/my")
    public Result<List<LandlordHouseDTO>> queryLandlordMyHouse(@RequestParam String landlordId) {
        return houseResourceService.queryLandlordMyHouse(landlordId);
    }
}