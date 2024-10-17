package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class SpecDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * specId,其实就是id
     */
    private Long specId;

    /**
     * spu名称
     */
    private String title;

    /**
     * specValue集合
     */
    private List<SpecValueDTO> specValueList;





}
