package com.zufang.renthouse.service;

import com.zufang.renthouse.entity.Landlord;
import com.zufang.renthouse.utils.Result;
import com.zufang.renthouse.dto.DeleteAccountDTO;
import com.zufang.renthouse.dto.UpdatePhoneDTO;

import java.util.List;

/**
 * 房东Service接口（放在service根包，和ContractService结构一致）
 */
public interface LandlordService {
    // 查询所有房东
    Result<List<Landlord>> listAllLandlord();

    // 根据ID查询房东
    Result<Landlord> getLandlordById(String landlordId);

    // 修改房东信息
    Result<?> updateLandlord(Landlord landlord);

    // 删除房东
    Result<?> deleteLandlord(String landlordId);

    // 新增房东（调用AddLandlord存储过程）
    Result<?> addLandlord(Landlord landlord);

    /**
     * 房东修改手机号
     */
    Result<?> updatePhone(UpdatePhoneDTO updatePhoneDTO);

    /**
     * 房东注销账户
     */
    Result<?> deleteAccount(DeleteAccountDTO deleteAccountDTO);
}