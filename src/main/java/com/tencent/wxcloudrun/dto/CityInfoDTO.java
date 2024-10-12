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
import java.util.List;

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
public class CityInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父类code")
    private String parentCode;

    @ApiModelProperty("子类")
    private List<CityInfoDTO> subRegin;

}
