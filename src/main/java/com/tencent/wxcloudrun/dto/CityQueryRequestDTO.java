package com.tencent.wxcloudrun.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省市县
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 20:50:44
 */
@ApiModel("省市县")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityQueryRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    private String numberCode;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 上级编码
     */
    @ApiModelProperty("上级编码")
    private String parentCode;

    /**
     * 长编码
     */
    @ApiModelProperty("长编码")
    private String longnumber;

    /**
     * 长名称
     */
    @ApiModelProperty("长名称")
    private String fullname;

}
