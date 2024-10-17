package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class SkuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long skuId;

    /**
     * spuId
     */
    private String spuId;
    /**
     * spu表title
     */
    private String spuName;
    /**
     * sku的spec信息：spec_value表id，long数组
     */
    private String specValueIds;
    /**
     * sku图片地址：字符串数组
     */
    private String skuImageStr;
    private List<String> skuImage;
    /**
     * 销售价格
     */
    private BigDecimal price;
    /**
     * 已卖数量
     */
    private Integer soldQuantity;
    /**
     * 可用库存
     */
    private Integer stockQuantity;
    /**
     * 安全库存
     */
    private Integer safeStockQuantity;
    /**
     * 重量：kg
     */
    private BigDecimal weight;
    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 删除状态(0未删除 1已删除)
     */
    private String deleted;
    /**
     * sku拥有的specValue值
     */
    private List<SpecValueDTO> specInfo;
    /**
     * sku拥有的价格类型及价格信息
     */
    private List<PriceInfoDTO> priceInfo;
    /**
     * sku库存信息
     */
    private List<StockInfoDTO> stockInfo;



}
