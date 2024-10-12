package com.tencent.wxcloudrun.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * 字段管理
 *  根据父级id,名字模糊查询子集数据
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 20:50:44
 */
@ApiModel("模糊查询字段值")
@Data
@Builder
public class DictionaryParentVagueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 上层归属
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("父级ObjectCode")
    private String parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
}
