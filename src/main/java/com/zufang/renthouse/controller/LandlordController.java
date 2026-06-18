package com.zufang.renthouse.controller;

import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.service.LandlordService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;
import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.service.LandlordService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 房东核心CRUD接口
 */
@RestController
@RequestMapping("/api/landlord")
public class LandlordController {

    @Autowired
    private LandlordService landlordService;

    /**
     * 查询所有房东
     */
    @GetMapping("/listAll")
    public Result<List<Landlord>> listAllLandlord() {
        return landlordService.listAllLandlord();
    }

    /**
     * 根据ID查询房东详情
     */
    @GetMapping("/getById")
    public Result<Landlord> getLandlordById(@RequestParam String landlordId) {
        return landlordService.getLandlordById(landlordId);
    }

    /**
     * 修改房东信息
     */
    @PutMapping("/update")
    public Result<?> updateLandlord(@Valid @RequestBody Landlord landlord) {
        return landlordService.updateLandlord(landlord);
    }

    /**
     * 删除房东
     */
    @DeleteMapping("/delete")
    public Result<?> deleteLandlord(@RequestParam String landlordId) {
        return landlordService.deleteLandlord(landlordId);
    }

    /**
     * 房东修改手机号
     * 请求URL：PUT http://localhost:8080/zufang/api/landlord/updatePhone
     */
    @PutMapping("/updatePhone")
    public Result<?> updatePhone(@Valid @RequestBody UpdatePhoneDTO updatePhoneDTO) {
        return landlordService.updatePhone(updatePhoneDTO);
    }

    /**
     * 房东注销账户
     * 请求URL：DELETE http://localhost:8080/zufang/api/landlord/deleteAccount
     */
    @DeleteMapping("/deleteAccount")
    public Result<?> deleteAccount(@Valid @RequestBody DeleteAccountDTO deleteAccountDTO) {
        return landlordService.deleteAccount(deleteAccountDTO);
    }
}
