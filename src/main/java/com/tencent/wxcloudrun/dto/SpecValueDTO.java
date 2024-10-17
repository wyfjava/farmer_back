package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class SpecValueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 其实就是id
     */
    private Long specValueId;
    /**
     * specId
     */
    private Long specId;
    /**
     * specValue
     */
    private String specValue;

}
