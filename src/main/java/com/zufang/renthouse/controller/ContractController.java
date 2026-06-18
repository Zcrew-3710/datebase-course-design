package com.zufang.renthouse.controller;

import com.zufang.renthouse.dto.*;
import com.zufang.renthouse.entity.Contract;
import com.zufang.renthouse.service.ContractService;
import com.zufang.renthouse.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    /**
     * 查询所有合同
     */
    @GetMapping("/listAll")
    public Result<List<Contract>> listAllContract() {
        try {
            List<Contract> list = contractService.listAllContract();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error("查询合同列表失败：" + e.getMessage());
        }
    }
    /**
     * 租客查询本人合同
     * 请求URL：GET http://localhost:8080/zufang/api/contract/tenant/own
     * 请求参数：tenantId（租客ID，如T001）
     */
    @GetMapping("/tenant/own")
    public Result<List<ContractResponseDTO>> queryTenantOwnContract(@RequestParam String tenantId) {
        return contractService.queryOwnContract(tenantId);
    }

    /**
     * 租客签订合同
     * 请求URL：POST http://localhost:8080/zufang/api/contract/sign
     * 请求体：SignContractDTO的JSON格式
     */
    @PostMapping("/sign")
    public Result<?> signContract(@Valid @RequestBody SignContractDTO dto) {
        return contractService.signContract(dto);
    }

    /**
     * 租客发起退租
     * 请求URL：PUT http://localhost:8080/zufang/api/contract/checkOut
     * 请求体：TenantCheckOutDTO的JSON格式
     */
    @PutMapping("/checkOut")
    public Result<?> tenantCheckOut(@Valid @RequestBody TenantCheckOutDTO dto) {
        return contractService.tenantCheckOut(dto);
    }

    /**
     * 房东查询本人合同（管理房源的所有合同）
     * 请求URL：GET http://localhost:8080/zufang/api/contract/landlord/own
     * 请求参数：landlordId（房东ID，如L001）
     */
    @GetMapping("/landlord/own")
    public Result<List<ContractResponseDTO>> queryLandlordOwnContract(@RequestParam String landlordId) {
        return contractService.queryLandlordOwnContract(landlordId);
    }


    /**
     * 房东发起退租
     * 请求URL：PUT http://localhost:8080/zufang/api/contract/landlord/checkOut
     * 请求体：LandlordCheckOutDTO的JSON格式
     */
    @PutMapping("/landlord/checkOut")
    public Result<?> landlordCheckOut(@Valid @RequestBody LandlordCheckOutDTO dto) {
        return contractService.landlordCheckOut(dto);
    }

    /**
     * 租客续租（仅更新合同截止日期）
     * 请求URL：PUT http://localhost:8080/zufang/api/contract/renewLease
     * 请求体：RenewLeaseDTO的JSON格式
     */
    @PutMapping("/renewLease")
    public Result<?> renewLease(@Valid @RequestBody RenewLeaseDTO dto) {
        return contractService.renewLease(dto);
    }
}
