package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("tb_land")
public class LandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 耕地名称
     */
    private String name;
    /**
     * 耕地面积
     */
    private BigDecimal area;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 耕地位置
     */
    private String location;
    /**
     * 启用状态（0启用1停用）
     */
    private Integer status;
    /**
     * 耕地描述
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
