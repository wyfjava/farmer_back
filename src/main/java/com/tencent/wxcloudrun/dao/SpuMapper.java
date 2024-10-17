package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.dto.SpuDTO;
import com.tencent.wxcloudrun.model.SpuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SpuMapper extends BaseMapper<SpuEntity> {

    List<SpuDTO> queryList(@Param("condition") Map<String, Object> params);
}
