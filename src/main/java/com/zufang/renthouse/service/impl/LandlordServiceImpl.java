package com.zufang.renthouse.service.impl;

import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.mapper.LandlordMapper;
import com.zufang.renthouse.service.LandlordService;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;

/**
 * 房东Service实现类（修正参数数量不匹配问题）
 */
@Service
public class LandlordServiceImpl implements LandlordService {

    @Autowired
    private LandlordMapper landlordMapper;

    @Override
    public Result<List<Landlord>> listAllLandlord() {
        List<Landlord> list = landlordMapper.listAllLandlord();
        return Result.success(list);
    }

    @Override
    public Result<Landlord> getLandlordById(String landlordId) {
        Landlord landlord = landlordMapper.getLandlordById(landlordId);
        if (landlord == null) {
            return Result.error("房东不存在");
        }
        return Result.success(landlord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateLandlord(Landlord landlord) {
        if (landlordMapper.getLandlordById(landlord.getLandlordId()) == null) {
            return Result.error("房东不存在");
        }
        landlordMapper.updateLandlord(landlord);
        return Result.success("房东信息修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteLandlord(String landlordId) {
        if (landlordMapper.getLandlordById(landlordId) == null) {
            return Result.error("房东不存在");
        }
        landlordMapper.deleteLandlord(landlordId);
        return Result.success("房东删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> addLandlord(Landlord landlord) {
        try {
            // ========== 核心修正：只传1个Landlord实体参数 ==========
            landlordMapper.addLandlordByProc(landlord);

            // 查询新增后的房东信息
            Landlord newLandlord = landlordMapper.getLandlordByIdCard(landlord.getIdCardNumber());
            return Result.success("房东新增成功", newLandlord);
        } catch (Exception e) {
            return Result.error("新增房东失败：" + e.getMessage());
        }
    }

    /**
     * 房东修改手机号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updatePhone(UpdatePhoneDTO updatePhoneDTO) {
        // 1. 校验房东是否存在
        Landlord landlord = landlordMapper.getLandlordByIdCard(updatePhoneDTO.getIdCardNumber());
        if (landlord == null) {
            return Result.error("房东不存在，无法修改手机号");
        }
        // 2. 修改手机号
        int rows = landlordMapper.updateLandlordPhoneByIdCard(
                updatePhoneDTO.getIdCardNumber(),
                updatePhoneDTO.getContactPhone()
        );
        if (rows > 0) {
            // 3. 返回修改后的房东信息
            landlord.setContactPhone(updatePhoneDTO.getContactPhone());
            return Result.success("手机号修改成功", landlord);
        } else {
            return Result.error("手机号修改失败");
        }
    }

    /**
     * 房东注销账户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteAccount(DeleteAccountDTO deleteAccountDTO) {
        // 1. 校验房东是否存在
        Landlord landlord = landlordMapper.getLandlordByIdCard(deleteAccountDTO.getIdCardNumber());
        if (landlord == null) {
            return Result.error("房东不存在，无法注销账户");
        }
        // 2. 注销账户（删除记录）
        int rows = landlordMapper.deleteLandlordByIdCard(deleteAccountDTO.getIdCardNumber());
        if (rows > 0) {
            return Result.success("账户注销成功");
        } else {
            return Result.error("账户注销失败");
        }
    }

}