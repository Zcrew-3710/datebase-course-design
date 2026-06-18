package com.zufang.renthouse.service.impl;

import com.zufang.renthouse.dto.*;
import com.zufang.renthouse.entity.Contract;
import com.zufang.renthouse.mapper.ContractMapper;
import com.zufang.renthouse.service.ContractService;
import com.zufang.renthouse.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void signContract(Contract contract) {
//        // 存储过程已做全量校验，直接调用
//        contractMapper.signContractByProc(contract);
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void returnHouse(Integer contractId, String returnType) {
//        if (!"正常".equals(returnType) && !"违约".equals(returnType)) {
//            throw new IllegalArgumentException("退租类型只能输入 正常 或 违约！");
//        }
//        contractMapper.returnHouseByProc(contractId, returnType);
//    }

    @Override
    public List<Contract> listAllContract() {
        return contractMapper.listAllContract();
    }

    @Override
    public Result<List<ContractResponseDTO>> queryOwnContract(String tenantId) {
        // 1. 校验租客ID非空
        if (tenantId == null || tenantId.trim().isEmpty()) {
            return Result.paramError("租客ID不能为空！");
        }

        try {
            // 2. 调用存储过程查询合同
            List<ContractResponseDTO> contractList = contractMapper.queryOwnContract(tenantId);

            // 3. 处理无合同场景
            if (contractList.isEmpty()) {
                return Result.success("暂无生效合同", contractList);
            }
            return Result.success("查询成功", contractList);

        } catch (DataAccessException e) {
            // 捕获存储过程抛出的错误（如租客ID不存在）
            String errorMsg = e.getMessage();
            if (errorMsg.contains("错误：租客ID")) {
                return Result.error(errorMsg.substring(errorMsg.indexOf("错误：")));
            }
            return Result.error("查询合同失败：" + errorMsg);
        }
    }

    /**
     * 签订合同（依赖存储过程的事务和校验）
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 兜底事务，和存储过程事务双层保障
    public Result<?> signContract(SignContractDTO dto) {
        try {
            // 调用存储过程签订合同
            contractMapper.signContract(dto);
            return Result.success("合同签订成功！", dto);
        } catch (DataAccessException e) {
            // 捕获存储过程抛出的所有错误（参数错误/数据不存在/业务冲突等）
            String errorMsg = e.getMessage();
            // 提取存储过程的核心错误信息（去掉MyBatis包装的冗余内容）
            if (errorMsg.contains("签订合同失败：")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("签订合同失败：") + 8);
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            return Result.error("系统异常：" + e.getMessage());
        }
    }

    /**
     * 租客退租（依赖存储过程的事务和信誉分调整逻辑）
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 兜底事务，和存储过程事务双层保障
    public Result<?> tenantCheckOut(TenantCheckOutDTO dto) {
        try {
            // 调用存储过程处理退租
            contractMapper.tenantCheckOut(dto);
            return Result.success("退租成功！", dto);
        } catch (DataAccessException e) {
            // 捕获存储过程抛出的所有业务错误
            String errorMsg = e.getMessage();
            // 提取核心错误信息（去掉MyBatis包装的冗余内容）
            if (errorMsg.contains("租客退租失败：")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("租客退租失败：") + 8);
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            return Result.error("系统异常：" + e.getMessage());
        }
    }

    @Override
    public Result<List<ContractResponseDTO>> queryLandlordOwnContract(String landlordId) {
        // 1. 基础参数校验：房东ID非空
        if (landlordId == null || landlordId.trim().isEmpty()) {
            return Result.paramError("房东ID不能为空！");
        }

        try {
            // 2. 调用存储过程查询合同
            List<ContractResponseDTO> contractList = contractMapper.queryLandlordOwnContract(landlordId);

            // 3. 处理无合同场景
            if (contractList.isEmpty()) {
                return Result.success("暂无生效合同", contractList);
            }
            return Result.success("查询成功", contractList);

        } catch (DataAccessException e) {
            // 捕获存储过程抛出的错误（如房东ID不存在）
            String errorMsg = e.getMessage();
            if (errorMsg.contains("错误：房东ID")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("错误："));
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            return Result.error("查询合同失败：" + e.getMessage());
        }
    }

    /**
     * 房东退租（依赖存储过程的事务和信誉分调整逻辑）
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 兜底事务，和存储过程事务双层保障
    public Result<?> landlordCheckOut(LandlordCheckOutDTO dto) {
        try {
            // 调用存储过程处理退租
            contractMapper.landlordCheckOut(dto);
            return Result.success("退租成功！", dto);
        } catch (DataAccessException e) {
            // 捕获存储过程抛出的所有业务错误
            String errorMsg = e.getMessage();
            // 提取核心错误信息（去掉MyBatis包装的冗余内容）
            if (errorMsg.contains("房东退租失败：")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("房东退租失败：") + 8);
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            return Result.error("系统异常：" + e.getMessage());
        }
    }

    /**
     * 租客续租（依赖存储过程的校验和更新逻辑）
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 兜底事务，和存储过程事务双层保障
    public Result<?> renewLease(RenewLeaseDTO dto) {
        try {
            // 调用存储过程处理续租
            contractMapper.renewLease(dto);
            return Result.success("续租成功！", dto);
        } catch (DataAccessException e) {
            // 捕获存储过程抛出的所有业务错误
            String errorMsg = e.getMessage();
            // 提取核心错误信息（去掉MyBatis包装的冗余内容）
            if (errorMsg.contains("续租失败：")) {
                errorMsg = errorMsg.substring(errorMsg.indexOf("续租失败：") + 6);
            }
            return Result.error(errorMsg);
        } catch (Exception e) {
            return Result.error("系统异常：" + e.getMessage());
        }
    }
}
