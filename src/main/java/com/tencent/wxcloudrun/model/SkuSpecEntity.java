package com.tencent.wxcloudrun.model;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description sku的spec_value关联表 此表已废弃！！！！！
 * @author BEJSON
 * @date 2024-10-12
 */
@Data
@TableName("tb_sku_spec")
public class SkuSpecEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 主键
     */
    private Long id;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * spec_value表id
     */
    private Long specValueId;

    /**
     * spec_value名称
     */
    private String specValue;

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