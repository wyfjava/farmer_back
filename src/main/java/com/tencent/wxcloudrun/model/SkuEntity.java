package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("tb_sku")
public class SkuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * spuId
     */
    private String spuId;
    /**
     * spu表title
     */
    private String spuName;
    /**
     * sku图片地址：字符串数组
     */
    private String skuImage;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 删除状态(0未删除 1已删除)
     */
    private String deleted;


}
