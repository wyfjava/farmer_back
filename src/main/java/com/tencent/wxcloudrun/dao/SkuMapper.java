package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.dto.SkuDTO;
import com.tencent.wxcloudrun.model.LandEntity;
import com.tencent.wxcloudrun.model.SkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkuMapper extends BaseMapper<SkuEntity> {

    List<SkuDTO> queryList(@Param("condition") Map<String, Object> params);

    List<SkuDTO> getSkuListBySpuId(@Param("spuId") Long spuId);
}
