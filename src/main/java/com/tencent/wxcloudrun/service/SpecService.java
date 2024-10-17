package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.SpecDTO;
import com.tencent.wxcloudrun.model.SpecEntity;

import java.util.List;

public interface SpecService extends BaseService<SpecEntity>{

    List<SpecDTO> getSpecListBySpecIdList(List<Long> specIdList);
}
