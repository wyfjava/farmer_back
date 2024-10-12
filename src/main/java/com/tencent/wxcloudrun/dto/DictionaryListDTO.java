package com.tencent.wxcloudrun.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class DictionaryListDTO implements Serializable {


    private static final long serialVersionUID = 2935402243755393260L;

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
    @ApiModelProperty(value = "上层归属,父级为0", example = "0", required = true)
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
     * 标识
     */
    @ApiModelProperty("标识")
    private int intSign;

    /**
     * 顺序
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("顺序")
    private String level;

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
     * 能否修改 1-可修改 2-不可修改
     */
    @ApiModelProperty("能否修改 1-可修改 2-不可修改")
    private String enableModify;


    private List<DictionaryListDTO> options;


    /**
     * 应用平台标识，请参考枚举
     */
    @ApiModelProperty("应用平台标识，请参考枚举")
    private String app;

}
