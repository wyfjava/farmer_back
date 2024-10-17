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
public class PriceInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer priceType;

    private String priceTypeName;

    private BigDecimal price;


}
