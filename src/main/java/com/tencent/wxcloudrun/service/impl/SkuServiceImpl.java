package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dao.SkuMapper;
import com.tencent.wxcloudrun.dto.LandDTO;
import com.tencent.wxcloudrun.dto.PriceInfoDTO;
import com.tencent.wxcloudrun.dto.SkuDTO;
import com.tencent.wxcloudrun.dto.StockInfoDTO;
import com.tencent.wxcloudrun.model.SkuEntity;
import com.tencent.wxcloudrun.service.SkuService;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("skuService")
@Slf4j
public class SkuServiceImpl extends BaseServiceImpl<SkuMapper, SkuEntity> implements SkuService {


    @Override
    public PageUtils<SkuDTO> queryPage(Map<String, Object> params) {
        log.info("SkuServiceImpl.queryPage.param:{}", JSON.toJSONString(params));
        Integer pageNum = Constant.pageNum;
        Integer pageSize = Constant.pageSize;
        if (params.get("page") != null) {
            pageNum = Integer.parseInt((String) params.get("page"));
        }
        if (params.get("limit") != null) {
            pageSize = Integer.parseInt((String) params.get("limit"));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SkuDTO> dtoList = baseMapper.queryList(params);
        PageInfo<SkuDTO> pageInfo = new PageInfo<>(dtoList);
        return new PageUtils<>(dtoList, (int) pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public List<SkuDTO> getSkuListBySpuId(Long spuId) {
        if (spuId == null) {
            return new ArrayList<>();
        }
        List<SkuDTO> skuList = baseMapper.getSkuListBySpuId(spuId);
        for (SkuDTO skuDTO : skuList) {
            //设置priceInfo
            List<PriceInfoDTO> priceInfoList = new ArrayList<>();
            priceInfoList.add(PriceInfoDTO.builder().price(skuDTO.getPrice()).build());
            List<StockInfoDTO> stockInfoList = new ArrayList<>();
            stockInfoList.add(StockInfoDTO.builder().stockQuantity(skuDTO.getStockQuantity()).safeStockQuantity(skuDTO.getSafeStockQuantity()).soldQuantity(skuDTO.getSoldQuantity()).build());
            skuDTO.setPriceInfo(priceInfoList);
            //设置stockInfo
            skuDTO.setStockInfo(stockInfoList);
            if (StringUtils.isNotEmpty(skuDTO.getSkuImageStr())) {
                skuDTO.setSkuImage(JSON.parseArray(skuDTO.getSkuImageStr(), String.class));
            }
        }
        return skuList;
    }
}
