package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dao.LandMapper;
import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.model.LandEntity;
import com.tencent.wxcloudrun.service.LandService;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("landService")
@Slf4j
public class LandServiceImpl extends BaseServiceImpl<LandMapper, LandEntity> implements LandService {

    @Override
    public PageUtils<LandDTO> queryPage(Map<String, Object> params) {
        log.info("LandServiceImpl.queryPage.param:{}", JSON.toJSONString(params));
      Integer pageNum = Constant.pageNum;
      Integer pageSize = Constant.pageSize;
      if (params.get("page") != null) {
        pageNum = (Integer) params.get("page");
      }
      if (params.get("limit") != null) {
        pageSize = (Integer) params.get("limit");
      }
      PageHelper.startPage(pageNum, pageSize);
      List<LandDTO> dtoList = baseMapper.queryList(params);
      PageInfo<LandDTO> pageInfo = new PageInfo<>(dtoList);
      return new PageUtils<>(dtoList, (int) pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }
}
