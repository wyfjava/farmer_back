package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @author zhengfeng
 */
@ApiModel("字段子项")
@Data
public class DictionaryOptionsDTO implements Serializable {

    @ApiModelProperty(value = "字典value", example = "男", required=true)
    private String value;

    @ApiModelProperty(value = "字典key", example = "0", required=true)
    private String label;
}
