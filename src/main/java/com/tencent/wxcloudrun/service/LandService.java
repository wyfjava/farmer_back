package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.model.LandEntity;
import com.tencent.wxcloudrun.utils.PageUtils;

import java.util.Map;

public interface LandService extends BaseService<LandEntity>{
    PageUtils<LandDTO> queryPage(Map<String, Object> params);
}
