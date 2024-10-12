package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.constant.RedisKeyConstant;
import com.tencent.wxcloudrun.dao.CityDao;
import com.tencent.wxcloudrun.dto.CityDTO;
import com.tencent.wxcloudrun.dto.CityInfoDTO;
import com.tencent.wxcloudrun.dto.CityQueryRequestDTO;
import com.tencent.wxcloudrun.model.CityEntity;
import com.tencent.wxcloudrun.service.CityService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.Query;
import com.tencent.wxcloudrun.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zhengfeng
 */
@Service("cityService")
public class CityServiceImpl extends ServiceImpl<CityDao, CityEntity> implements CityService {
    @Autowired
    private RedisUtils redisUtils;

    //用区域编码查询
    @Override
    public CityEntity getByAreaCode(String areaCode) {
        CityEntity entity = baseMapper.getByAreaCode(areaCode);
        return entity;
    }


    @Override
    public PageUtils<CityDTO> queryPage(Map<String, Object> params) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("deleted", "0");

        IPage page = this.page(
                new Query<CityEntity>().getPage(params),
                qw
        );

        List records = page.getRecords();
        List<CityDTO> dtoList = ConvertUtils.sourceToTarget(records, CityDTO.class);
        page.setRecords(dtoList);

        return new PageUtils(page);
    }

    @Override
    public List<CityDTO> list(Map<String, Object> params) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("deleted", "0");

        List<CityEntity> entityList = baseMapper.selectList(qw);

        return ConvertUtils.sourceToTarget(entityList, CityDTO.class);
    }

    /**
     * 逻辑删除，只更新删除标记
     *
     * @param id
     */
    @Override
    public void removeByDelete(String id) {
        CityEntity entity = new CityEntity();
        entity.setId(Long.parseLong(id));
        entity.setDeleted("1");
        baseMapper.updateById(entity);
    }

    /**
     * 查询所有的省
     *
     * @return
     */
    @Override
    public List<CityDTO> findAllProvince() {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("deleted", "0");
        qw.eq("parent_code", "china");

        List<CityEntity> cityEntities = baseMapper.selectList(qw);
        List<CityDTO> cityDTOS = ConvertUtils.sourceToTarget(cityEntities, CityDTO.class);

        return cityDTOS;
    }

    /**
     * 根据当前节点查询下级节点数据
     *
     * @param parentCode 父节点
     * @return
     */
    @Override
    public List<CityDTO> findAllNode(String parentCode) {

        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("deleted", "0");
        qw.eq("parent_code", parentCode);

        List<CityEntity> cityEntities = baseMapper.selectList(qw);
        List<CityDTO> cityDTOS = ConvertUtils.sourceToTarget(cityEntities, CityDTO.class);

        return cityDTOS;

    }

    /**
     * 获取所有省市信息
     *
     * @return
     */
    @Override
    public List<CityEntity> findAll() {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("deleted", "0");

        List<CityEntity> cityEntities = baseMapper.selectList(qw);
        return cityEntities;
    }

    @Override
    public List<CityInfoDTO> findCityTree() {
        String key = RedisKeyConstant.QX_CITY_INFO;
        Object o = redisUtils.get(key);
        if (o != null){
            String json = o.toString();
            try {
                List<CityInfoDTO> dtoList = JSON.parseArray(json, CityInfoDTO.class);
                return dtoList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 不存在先查库，然后再写入库
        List<CityEntity> all = this.findAll();
        List<CityInfoDTO> dtoList = this.toCityTree(all, "china");

        // 写入缓存
        redisUtils.set(key, JSON.toJSONString(dtoList));
        return dtoList;
    }

    //树形结构
    @Override
    public List<CityInfoDTO> toCityTree(List<CityEntity> regions, String parentAreaCode) {
        return regions.stream()
                .filter(x ->x.getParentCode().equals(parentAreaCode) && x.getDeleted().equals("0"))
                .map(x ->
                        CityInfoDTO.builder()
                        .code(x.getNumberCode())
                        .name(x.getName())
                        .parentCode(x.getParentCode())
                        .subRegin(toCityTree(regions, x.getNumberCode()))
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<CityDTO> findTree() {
        List<CityEntity> all = this.findAll();
        return this.toTree(all, "china");
    }


    //树形结构
    @Override
    public List<CityDTO> toTree(List<CityEntity> regions, String parentAreaCode) {
        return regions.stream()
                .filter(x -> x.getParentCode().equals(parentAreaCode) && x.getDeleted().equals("0"))
                //.sorted(Comparator.comparing(CityDTO::getSort))
                .map(x ->
                        CityDTO.builder()
                                .numberCode(x.getNumberCode())
                                .name(x.getName())
                                .parentCode(x.getParentCode())
                                .postalcode(x.getPostalcode())
                                .longnumber(x.getLongnumber())
                                .fullname(x.getFullname())
                                .level(x.getLevel())
                                .parentCode(x.getParentCode())
                                .subRegin(toTree(regions, x.getNumberCode()))
                                .build()

                )
                .collect(Collectors.toList());
    }

    /**
     * 根据numberCode 查询数据
     *
     * @param regionCode
     * @return
     */
    @Override
    public CityEntity getByNumberCode(String regionCode) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("number_code", regionCode);
        CityEntity cityEntity = null;
        try {
            cityEntity = baseMapper.selectOne(qw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityEntity;
    }

    @Override
    public  CityEntity getByLongNumber(String longNumber) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("longnumber", longNumber);
        CityEntity cityEntity = null;
        try {
            cityEntity = baseMapper.selectOne(qw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityEntity;
    }

    /**
     * 根据地区号查询出省市编号
     *
     * @param hostCity
     * @return
     */
    @Override
    public CityEntity querySF(String hostCity) {
        hostCity = hostCity.substring(0, 2) + "0000";
        return baseMapper.querySF(hostCity);
    }

    public CityEntity getByName(String name) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<CityEntity>();

        qw.eq("name", name);
        qw.last("limit 1");
        CityEntity cityEntity = null;
        try {
            cityEntity = baseMapper.selectOne(qw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityEntity;
    }

    @Override
    public List<CityEntity> queryByLevel(String level) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<>();
        qw.eq("level",level);
        qw.eq("deleted","0");
        List<CityEntity> entityList = baseMapper.selectList(qw);
        return entityList;
    }

    @Override
    public List<CityDTO> getCityInfo(CityDTO cityDTO) {
        String longnumber=cityDTO.getLongnumber();
        if(StringUtils.isNotBlank(cityDTO.getLongnumber())){
            QueryWrapper<CityEntity> qw = new QueryWrapper<>();
            qw.eq("longnumber",longnumber);
            qw.eq("deleted","0");
            List<CityEntity> entityList = baseMapper.selectList(qw);
           if(CollectionUtils.isNotEmpty(entityList)){
               List<CityDTO> dtos=ConvertUtils.sourceToTarget(entityList,CityDTO.class);
               return dtos;
           }
        }
        return null;
    }

    /**
     * 根据条件查询列表信息
     */
    @Override
    public List<CityDTO> queryCityInfo(CityQueryRequestDTO requestDTO) {
        QueryWrapper<CityEntity> qw = new QueryWrapper<>();
        if(StringUtils.isNotBlank(requestDTO.getNumberCode())){
            qw.eq("number_code",requestDTO.getNumberCode());
        }
        if(StringUtils.isNotBlank(requestDTO.getLongnumber())){
            qw.eq("longnumber",requestDTO.getLongnumber());
        }
        if(StringUtils.isNotBlank(requestDTO.getName())){
            qw.eq("name",requestDTO.getName());
        }
        if(StringUtils.isNotBlank(requestDTO.getParentCode())){
            qw.eq("parent_code",requestDTO.getParentCode());
        }
        if(StringUtils.isNotBlank(requestDTO.getFullname())){
            qw.like("fullname",requestDTO.getFullname());
        }
        qw.eq("deleted","0");
        List<CityEntity> entityList = baseMapper.selectList(qw);
        if(CollectionUtils.isNotEmpty(entityList)){
            List<CityDTO> dtos=ConvertUtils.sourceToTarget(entityList,CityDTO.class);
            return dtos;
        }
        return new ArrayList<>();
    }

    @Override
    public List<CityDTO> findTreeAll() {
        List<CityEntity> all = this.findAll();
        return this.toTree(all, "");
    }

}
