package com.tencent.wxcloudrun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 可用库存
     */
    private Integer stockQuantity;
    /**
     * 安全库存
     */
    private Integer safeStockQuantity;
    /**
     * 已卖数量
     */
    private Integer soldQuantity;

}
