package com.tencent.wxcloudrun.dto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class LandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
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


}
