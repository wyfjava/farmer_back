package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.model.LandEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LandMapper extends BaseMapper<LandEntity> {


    List<LandDTO> queryList(@Param("condition") Map<String, Object> params);
}
