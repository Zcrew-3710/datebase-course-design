package com.zufang.renthouse.service;

import com.zufang.renthouse.dto.*;
import com.zufang.renthouse.entity.Contract;
import com.zufang.renthouse.utils.Result;
import java.util.List;

public interface ContractService {

//    /**
//     * 退租处理（调用存储过程）
//     */
//    void returnHouse(Integer contractId, String returnType);

    /**
     * 查询所有合同
     */
    List<Contract> listAllContract();

    /**
     * 租客查询本人合同
     * @param tenantId 租客ID（如T001）
     * @return 合同列表（含甲方/乙方/房源/合同信息）
     */
    Result<List<ContractResponseDTO>> queryOwnContract(String tenantId);

    /**
     * 租客签订合同
     * @param dto 签订合同的请求参数
     * @return 操作结果
     */
    Result<?> signContract(SignContractDTO dto);

    /**
     * 租客发起退租（调整信誉分+删除合同+更新房源状态）
     * @param dto 退租请求参数
     * @return 操作结果
     */
    Result<?> tenantCheckOut(TenantCheckOutDTO dto);

    /**
     * 房东查询本人合同（管理房源的所有合同）
     * @param landlordId 房东ID（如L001）
     * @return 合同列表
     */
    Result<List<ContractResponseDTO>> queryLandlordOwnContract(String landlordId);

    /**
     * 房东发起退租（调整信誉分+删除合同+更新房源状态）
     * @param dto 退租请求参数
     * @return 操作结果
     */
    Result<?> landlordCheckOut(LandlordCheckOutDTO dto);

    /**
     * 租客续租（仅更新合同截止日期，校验租客信誉分+新日期合法性）
     * @param dto 续租请求参数
     * @return 操作结果
     */
    Result<?> renewLease(RenewLeaseDTO dto);
}