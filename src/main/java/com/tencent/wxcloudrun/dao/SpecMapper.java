package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.dto.SpecDTO;
import com.tencent.wxcloudrun.model.SpecEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecMapper extends BaseMapper<SpecEntity> {

    List<SpecDTO> getSpecListBySpecIdList(@Param("specIdList") List<Long> specIdList);
}
