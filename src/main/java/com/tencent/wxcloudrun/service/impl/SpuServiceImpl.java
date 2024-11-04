package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dao.SpuMapper;
import com.tencent.wxcloudrun.dto.*;
import com.tencent.wxcloudrun.model.SpuEntity;
import com.tencent.wxcloudrun.service.SkuService;
import com.tencent.wxcloudrun.service.SpecService;
import com.tencent.wxcloudrun.service.SpuService;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("spuService")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpuServiceImpl extends BaseServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    private final SpecService specService;
    private final SkuService skuService;

    @Override
    public PageUtils<SpuDTO> queryPage(Map<String, Object> params) {
        log.info("SpuServiceImpl.queryPage.param:{}", JSON.toJSONString(params));
        Integer pageNum = Constant.pageNum;
        Integer pageSize = Constant.pageSize;
        if (params.get("page") != null) {
            pageNum = Integer.parseInt((String) params.get("page"));
        }
        if (params.get("limit") != null) {
            pageSize = Integer.parseInt((String) params.get("limit"));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<SpuDTO> dtoList = baseMapper.queryList(params);
        setExtraInfo(dtoList);
        PageInfo<SpuDTO> pageInfo = new PageInfo<>(dtoList);
        return new PageUtils<>(dtoList, (int) pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public SpuDTO spuDetail(Map<String, Object> params) {
        SpuDTO spuDTO = baseMapper.spuDetail(params);
        if (spuDTO != null) {
            spuDTO.setDesc(JSON.parseArray(spuDTO.getDescImgs(), String.class));
            spuDTO.setImages(JSON.parseArray(spuDTO.getImagesStr(), String.class));
            spuDTO.setSpuTagList(getSpuTagListBySpuTat(spuDTO.getSpuTag()));
            // 设置specList
            spuDTO.setSpecList(getSpecListBySpecId(spuDTO.getSpecId()));
            // 设置skuList
            spuDTO.setSkuList(getSkuListBySpuId(spuDTO.getId()));
        }
        return spuDTO;
    }

    private void setExtraInfo(List<SpuDTO> dtoList) {
        for (SpuDTO spuDTO : dtoList) {
            spuDTO.setDesc(JSON.parseArray(spuDTO.getDescImgs(), String.class));
            spuDTO.setImages(JSON.parseArray(spuDTO.getImagesStr(), String.class));
            spuDTO.setSpuTagList(getSpuTagListBySpuTat(spuDTO.getSpuTag()));
            // 设置specList
            spuDTO.setSpecList(getSpecListBySpecId(spuDTO.getSpecId()));
            // 设置skuList
            spuDTO.setSkuList(getSkuListBySpuId(spuDTO.getId()));
        }
    }

    /**
     * 根据spu的id获取sku集合
     * @param spuId
     * @return
     */
    private List<SkuDTO> getSkuListBySpuId(Long spuId) {
        return skuService.getSkuListBySpuId(spuId);
    }

    /**
     * 获取spu拥有的spec信息
     * @param specId 多个逗号分隔
     * @return
     */
    private List<SpecDTO> getSpecListBySpecId(String specId) {
        List<Long> specIdList = JSON.parseArray(specId, Long.class);
        return specService.getSpecListBySpecIdList(specIdList);
    }

    private List<SpuTagDTO> getSpuTagListBySpuTat(String spuTag) {
        List<SpuTagDTO> list = new ArrayList<>();
        List<String> strings = JSON.parseArray(spuTag, String.class);
        for (String string : strings) {
            SpuTagDTO temp = new SpuTagDTO();
            temp.setTitle(string);
            list.add(temp);
        }
        return list;
    }
}
