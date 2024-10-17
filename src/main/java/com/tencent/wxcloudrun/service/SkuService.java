package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.SkuDTO;
import com.tencent.wxcloudrun.model.SkuEntity;
import com.tencent.wxcloudrun.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface SkuService extends BaseService<SkuEntity>{

    PageUtils<SkuDTO> queryPage(Map<String, Object> params);

    /**
     * 根据spu获取其下的sku信息
     * @param spuId
     * @return
     */
    List<SkuDTO> getSkuListBySpuId(Long spuId);
}
