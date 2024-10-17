package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class SpuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * 分类：取字典表的一级分类id
     */
    private Long spuCategoryId;

    /**
     * spu名称
     */
    private String title;

    /**
     * 主图地址
     */
    private String primaryImage;

    /**
     * 详情页轮播图片地址：字符串数组
     */
    private String imagesStr;
    private List<String> images;

    /**
     * 视频地址
     */
    private String video;

    /**
     * spu标签：符串数组
     */
    private String spuTag;

    /**
     * 最低售卖价格
     */
    private BigDecimal minSalePrice;

    /**
     * 最高售卖价格
     */
    private BigDecimal maxLinePrice;

    /**
     * 库存数量
     */
    private Integer spuStockQuantity;

    /**
     * 已售数量
     */
    private Integer soldNum;

    /**
     * 是否上架销售
     */
    private Integer isPutOnSale;

    /**
     * 是否售罄：0否1是
     */
    private Integer isSoldOut;

    /**
     * spu拥有的spec，long数组
     */
    private String specId;

    /**
     * 详情页详情描述图片地址：字符串数组
     */
    private String descImgs;
    private List<String> desc;

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

    private List<SpuTagDTO> spuTagList;
    /**
     * spu所拥有的spec信息
     */
    private List<SpecDTO> specList;
    /**
     * spu下的sku
     */
    private List<SkuDTO> skuList;
}
