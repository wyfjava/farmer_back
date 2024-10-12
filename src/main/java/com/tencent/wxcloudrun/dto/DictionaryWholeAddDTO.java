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
public class DictionaryWholeAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 上层归属
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "上层归属，顶级为0", example = "0", required=true)
    private String parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required=true)
    private String name;

    /**
     * 标识
     */
    @ApiModelProperty(value = "标识", required=true)
    private String sign;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序", required=true)
    private String level;

    /**
     * 启用状态（0启用1停用）
     */
    @ApiModelProperty(value = "启用状态（0启用1停用）", required=true)
    private String status;

    /**
     * 字段图标
     */
    @ApiModelProperty(value = "字段图标", required=true)
    private String icon;

    @ApiModelProperty(value = "子集数据", required=true)
    private List<DictionaryOptionsDTO> options;

}
