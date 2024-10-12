package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("根据sign字典信息")
@Data
public class DictionaryBySignDTO implements Serializable {


    private static final long serialVersionUID = 3725134483296221815L;
    /**
     * 查询主键code
     */
    @ApiModelProperty(value = "查询主键code")
    private String objectCode1;
    /**
     * 查询主键code
     */
    @ApiModelProperty(value = "查询主键code")
    private String objectCode2;
    /**
     * 查询主键code
     */
    @ApiModelProperty(value = "查询主键code")
    private String objectCode3;

    @ApiModelProperty(value = "顶级的编码")
    private String parentId;
  /*  @ApiModelProperty(value = "一级的编码")
    private String parentId1;*/

    /**
     * 名称
     */
    @ApiModelProperty("一级名称")
    private String name1;
    /**
     * 名称
     */
    @ApiModelProperty("二级名称")
    private String name2;
    /**
     * 名称
     */
    @ApiModelProperty("三级名称")
    private String name3;

    /**
     * 标识
     */
    @ApiModelProperty("一级标识")
    private String sign1;
    /**
     * 标识
     */
    @ApiModelProperty("二级标识")
    private String sign2;
    /**
     * 标识
     */
    @ApiModelProperty("三级标识")
    private String sign3;


}