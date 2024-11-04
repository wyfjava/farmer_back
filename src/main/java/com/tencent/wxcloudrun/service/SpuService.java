package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.dto.SpuDTO;
import com.tencent.wxcloudrun.model.SpuEntity;
import com.tencent.wxcloudrun.utils.PageUtils;

import java.util.Map;

public interface SpuService extends BaseService<SpuEntity>{

    PageUtils<SpuDTO> queryPage(Map<String, Object> params);

    SpuDTO spuDetail(Map<String, Object> params);
}
