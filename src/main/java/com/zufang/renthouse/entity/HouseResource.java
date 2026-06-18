package com.zufang.renthouse.entity;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class HouseResource {
    @NotBlank(message = "区域号不能为空")
    private String areaNo;

    @NotBlank(message = "楼栋号不能为空")
    private String buildingNo;

    @NotBlank(message = "房间号不能为空")
    private String roomNo;

    @NotBlank(message = "房间类型不能为空")
    private String roomType;

    @NotNull(message = "月租金不能为空")
    @Positive(message = "月租金必须大于0")
    private BigDecimal rent; // 存储过程要求>0

    @PositiveOrZero(message = "押金不能为负数")
    private BigDecimal deposit = BigDecimal.ZERO; // 默认为0

    private String roomStatus = "空闲"; // 默认空闲

    @NotBlank(message = "房东ID不能为空")
    private String landlordId; // 绑定上传房源的房东ID
}