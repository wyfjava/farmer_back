package com.tencent.wxcloudrun.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 20:50:44
 */
@ApiModel("字段管理")
@Data
@Builder
public class DictionaryDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 查询主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键", example = "1000000000000000", required = true)
    private String objectCode;

    /**
     * 上层归属
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上层归属")
    private String parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 标识
     */
    @ApiModelProperty("标识")
    private String sign;

    /**
     * 顺序
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("顺序")
    private int level;

    /**
     * 启用状态（0启用1停用）
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("启用状态（0启用1停用）")
    private String status;

    /**
     * 字段图标
     */
    @ApiModelProperty("字段图标")
    private String icon;

    /**
     * 应用平台标识，请参考枚举
     */
    @ApiModelProperty("应用平台标识，请参考枚举")
    private String app;


    private List<DictionaryDTO> options;

}
