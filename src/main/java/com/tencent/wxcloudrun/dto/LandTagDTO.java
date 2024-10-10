package com.tencent.wxcloudrun.dto;
import lombok.Data;

import java.io.Serializable;


@Data
public class LandTagDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 耕地id
     */
    private Long landId;
    private Long tagId;

    /**
     * 标签名称
     */
    private String title;
}
