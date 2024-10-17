package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.tencent.wxcloudrun.dao.SpecMapper;
import com.tencent.wxcloudrun.dto.SpecDTO;
import com.tencent.wxcloudrun.model.SpecEntity;
import com.tencent.wxcloudrun.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("specService")
@Slf4j
public class SpecServiceImpl extends BaseServiceImpl<SpecMapper, SpecEntity> implements SpecService {


    @Override
    public List<SpecDTO> getSpecListBySpecIdList(List<Long> specIdList) {
        if(CollectionUtils.isEmpty(specIdList)){
            return Lists.newArrayList();
        }
        return baseMapper.getSpecListBySpecIdList(specIdList);
    }
}
