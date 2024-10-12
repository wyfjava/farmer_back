package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
@Data
@TableName("qx_dictionary")
public class DictionaryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 查询主键
     */
    @TableField(fill = FieldFill.INSERT)
    private String objectCode;
    /**
     * 上层归属
     */
    private String parentId;
    /**
     * 名称
     */
    private String name;
    /**
     * 标识
     */
    private String sign;
    /**
     * 顺序
     */
    private String level;
    /**
     * 启用状态（0启用1停用）
     */
    private String status;
    /**
     * 字段图标
     */
    private String icon;

    /**
     * 应用平台标识，请参考枚举
     */
    private String app;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 删除状态(0未删除 1已删除)
     */
    private String deleted;


    /**
     * 能否修改 1-可修改 2-不可修改
     */
    private String enableModify;

}
